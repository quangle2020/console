package com.quanglv.service.impl;

import com.quanglv.config.FilesConfig;
import com.quanglv.domain.second.Transactions;
import com.quanglv.repository.TransactionsRepositoryImpl;
import com.quanglv.repository.second.TransactionsRepository;
import com.quanglv.service.FilesService;
import com.quanglv.type.FileExtensionTypes;
import com.quanglv.type.FileTypes;
import com.quanglv.type.SourcesTypes;
import com.quanglv.utils.DateUtil;
import com.quanglv.utils.FilesUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransactionsServiceImpl {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private FilesConfig filesConfig;

    @Autowired
    private FilesService filesService;

    @Autowired
    private TransactionsRepositoryImpl transactionsRepositoryImpl;

    public String registerReport() {

        CompletableFuture.runAsync(() -> {
            try {
                createExcelFromDataInDb();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        return "Đăng kí nhận sao kê thành công";
    }

    public void createExcelFromDataInDb() throws IOException {

        // Get data in DB
//        List<Transactions> transactionsList = transactionsRepository.findAll();
        List<Transactions> transactionsList = transactionsRepositoryImpl.getTransactions();

        // Get inputStream in template

        Path templatePath = Paths.get(filesConfig.getTemplateDirectory().replace("file:", "") + "/" + "thanh-toan-hoa-don.xlsx").toAbsolutePath();

        InputStream inputStream = Files.newInputStream(templatePath);

        Workbook workbook = new XSSFWorkbook(inputStream);

        long startTime = System.currentTimeMillis();

        Sheet sheet = workbook.getSheetAt(0);

        CellStyle orderStyle = sheet.getRow(4).getCell(0).getCellStyle();
        CellStyle idStyle = sheet.getRow(4).getCell(1).getCellStyle();
        CellStyle valueStyle = sheet.getRow(4).getCell(2).getCellStyle();

        AtomicInteger rowIndex = new AtomicInteger(0);

        // set total
        Row totalRow = sheet.getRow(4);
        totalRow.getCell(3).setCellValue(transactionsList.size());

        Row dateRow = sheet.getRow(4);
        dateRow.getCell(5).setCellValue(DateUtil.convertDateToString(new Date()));

        if (!CollectionUtils.isEmpty(transactionsList))
            transactionsList.forEach(t -> {
                Row row = createRow(sheet, rowIndex.get() + 4);

                setCellValue(row, 0, rowIndex.get() + 1, orderStyle);
                setCellValue(row, 1, t.getId(), idStyle);

                DecimalFormat df = new DecimalFormat("###,###,###");
                setCellValue(row, 2, df.format(t.getPrice()) + " vnđ", valueStyle);

                rowIndex.getAndIncrement();
            });

        System.out.println("--> So luong giao dich: " + transactionsList.size());

        filesService.writeExcelFile(workbook,
                filesConfig.getRootDirectory(),
                SourcesTypes.PUBLIC.getCode(),
                FileExtensionTypes.XLSX.getCode());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        System.out.println("--> Tong thoi gian xu ly: " + elapsedTime / 1000 + "s");
    }

    private Row createRow(Sheet sheet, Integer index) {
        if (Objects.isNull(sheet.getRow(index)))
            sheet.createRow(index);
        return sheet.getRow(index);
    }

    private void setCellValue(Row row, Integer index, Object value, CellStyle cellStyle) {
        Cell cell;

        if (Objects.isNull(row.getCell(index)))
            cell = row.createCell(index);
        else cell = row.getCell(index);

        cell.setCellValue(String.valueOf(value));
        cell.setCellStyle(cellStyle);
    }

    public String testExcel() throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
        for (int i = 0; i < 3; i++) {

            Sheet sh = wb.createSheet();
            for (int rownum = 0; rownum < 1000000; rownum++) {
                Row row = sh.createRow(rownum);
                for (int cellnum = 0; cellnum < 10; cellnum++) {
                    Cell cell = row.createCell(cellnum);
                    String address = new CellReference(cell).formatAsString();
                    cell.setCellValue(address);
                }
                // manually control how rows are flushed to disk
                if (rownum % 100 == 0) {
                    ((SXSSFSheet) sh).flushRows(100); // retain 100 last rows and flush all others
                    // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
                    // this method flushes all rows
                }
            }
        }

        String fileName = "\\" + UUID.randomUUID().toString()
                + "-" + DateUtil.convertDateToString(new Date()).replace(" ", "");

        return filesService.writeExcelFile(wb,
                filesConfig.getRootDirectory(),
                SourcesTypes.PUBLIC.getCode(),
                fileName);
    }
}

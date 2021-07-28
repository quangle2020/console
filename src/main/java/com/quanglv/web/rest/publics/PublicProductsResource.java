package com.quanglv.web.rest.publics;

import com.quanglv.domain.second.Transactions;
import com.quanglv.repository.second.TransactionsRepository;
import com.quanglv.service.ProductsService;
import com.quanglv.service.dto.ProductsDTO;
import com.quanglv.service.dto.ProductsSearchRequestDTO;
import com.quanglv.service.impl.TransactionsServiceImpl;
import com.quanglv.utils.error.CustomizeException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "")
public class PublicProductsResource {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TransactionsServiceImpl transactionsService;

    @PostMapping(value = "/products/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestBody ProductsSearchRequestDTO request) {
        return ResponseEntity.ok(productsService.search(request));
    }

    @GetMapping(value = "/excel")
    public ResponseEntity<?> excel() {
        return ResponseEntity.ok(transactionsService.registerReport());
    }

    @GetMapping(value = "/test-excel")
    public ResponseEntity<?> getAll() throws IOException {
        return ResponseEntity.ok(transactionsService.testExcel());
    }

    @GetMapping(value = "/transactions")
    public String saveTransactions(){

        List<Transactions> transactions = new ArrayList<>();

        for(int i = 0; i < 10000; i++){
            BigDecimal max = new BigDecimal(342343247);
            BigDecimal randFromDouble = new BigDecimal(Math.random());
            BigDecimal actualRandomDec = randFromDouble.multiply(max);
            actualRandomDec = actualRandomDec
                    .setScale(2, BigDecimal.ROUND_DOWN);
            transactions.add(new Transactions(UUID.randomUUID().toString(), actualRandomDec));
        }

        transactionsRepository.saveAll(transactions);
        return "Success";
    }
}

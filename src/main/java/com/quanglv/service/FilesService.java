package com.quanglv.service;

import com.quanglv.service.dto.DownloadFileResponseDTO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FilesService {
    Resource downloadTemplate(String fileName) throws MalformedURLException;

    DownloadFileResponseDTO downloadPublicFile(String fileId) throws MalformedURLException;

    String uploadFile(MultipartFile file);

    String writeExcelFile(Workbook workbook, String rootDir, String folder, String fileType);
}

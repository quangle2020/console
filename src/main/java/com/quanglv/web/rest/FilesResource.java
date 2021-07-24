package com.quanglv.web.rest;

import com.quanglv.service.FilesService;
import com.quanglv.service.dto.DownloadFileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@RequestMapping(value = "")
public class FilesResource {

    @Autowired
    private FilesService filesService;

    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadInternal(@ModelAttribute MultipartFile file) {
        return ResponseEntity.ok(filesService.uploadFile(file));
    }

    @GetMapping("/file/download/template")
    public ResponseEntity<Resource> downloadTemplate(@RequestParam String fileName) throws MalformedURLException {
        Resource resource = filesService.downloadTemplate(fileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/file/download")
    public ResponseEntity<Resource> downloadPublicFile(@RequestParam String fileId) throws MalformedURLException {
        DownloadFileResponseDTO responseDTO = filesService.downloadPublicFile(fileId);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + responseDTO.getFileName()  + "\"");

        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(responseDTO.getResource());
    }
}

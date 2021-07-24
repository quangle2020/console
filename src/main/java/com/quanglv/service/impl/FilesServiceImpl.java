package com.quanglv.service.impl;

import com.quanglv.config.FilesConfig;
import com.quanglv.domain.first.FileStorages;
import com.quanglv.repository.first.FilesStoragesRepository;
import com.quanglv.service.FilesService;
import com.quanglv.service.dto.DownloadFileResponseDTO;
import com.quanglv.type.FileExtensionTypes;
import com.quanglv.type.FileTypes;
import com.quanglv.type.SourcesTypes;
import com.quanglv.type.StatusTypes;
import com.quanglv.utils.FilesUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesConfig filesConfig;

    @Autowired
    private FilesStoragesRepository filesStoragesRepository;

    @Override
    public Resource downloadTemplate(String fileName) throws MalformedURLException {
        String url = filesConfig.getTemplateDirectory() + fileName;
        Resource resource = new UrlResource(url);
        if (!resource.exists())
            return null;
        return resource;
    }

    @Override
    public DownloadFileResponseDTO downloadPublicFile(String fileId) throws MalformedURLException {
        FileStorages entity = filesStoragesRepository.findById(fileId).orElse(null);

        if(Objects.isNull(entity))
            return null;

        String url = filesConfig.getRootDirectory()
                + "/" + entity.getFilePath()
                + "/" + entity.getFileName()
                + "." + entity.getFileExtension();

        Resource resource = new UrlResource(url);

        if (!resource.exists())
            return null;

        return DownloadFileResponseDTO
                .builder()
                .resource(resource)
                .fileName(entity.getFileName() + "." + entity.getFileExtension())
                .build();
    }

    @Override
    public String uploadFile(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();
        String fileExtension = FilesUtil.getExtensionFile(file.getOriginalFilename());

        FilesUtil.copyFileToDisk(file, filesConfig.getRootDirectory(), SourcesTypes.PUBLIC.getCode(), fileName, fileExtension);

        return saveFileInfo(
                fileName,
                SourcesTypes.PUBLIC.getCode(),
                fileExtension,
                FileTypes.PUBLIC.getCode(),
                file.getSize());

    }

    @Override
    public String writeExcelFile(Workbook workbook, String rootDir, String folder, String fileType) {
        String fileName = UUID.randomUUID().toString();

        Long fileSize = FilesUtil.writeExcelFileToDisk(workbook,
                filesConfig.getRootDirectory(),
                SourcesTypes.PUBLIC.getCode(),
                fileName,
                FileExtensionTypes.XLSX.getCode());

        return saveFileInfo(fileName,
                SourcesTypes.PUBLIC.getCode(),
                FileExtensionTypes.XLSX.getCode(),
                FileTypes.INTERNAL.getCode(),
                fileSize);
    }

    public String saveFileInfo(String fileName, String sourceType, String fileExtension, String fileType, Long fileSize) {
        FileStorages entity = new FileStorages();
        entity.setId(UUID.randomUUID().toString());
        entity.setFileName(fileName);
        entity.setFileExtension(fileExtension);
        entity.setFilePath(sourceType);
        entity.setFileType(fileType);
        entity.setFileSize(fileSize);
        entity.setStorageDays(1);
        entity.setStatus(StatusTypes.ACTIVE.getCode());
        entity.setCreatedDate(Instant.now());
        entity.setUpdatedDate(Instant.now());
        entity.setCreatedUser(1L);
        entity.setUpdatedUser(1L);
        return filesStoragesRepository.save(entity).getId();
    }
}

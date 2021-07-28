package com.quanglv.utils;

import com.quanglv.constant.Constants;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

public class FilesUtil {

    public static String getExtensionFile(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static String genNewFileName(String fileName) {
        return new StringBuilder()
                .append(UUID.randomUUID().toString())
                .append(".")
                .append(getExtensionFile(fileName))
                .toString();
    }

    public static Long writeExcelFileToDisk(Workbook wb, String rootDir, String folder, String fileName, String fileType) {
        File currDir = new File(rootDir.replace("file:", "") + "/" + folder);
        String path = currDir.getAbsolutePath();
        String fileLocation = path
                + "\\" + fileName
                + "." + fileType;

        FileOutputStream out;
        Long size = 0L;
        try {
            out = new FileOutputStream(fileLocation);
            wb.write(out);
            wb.close();
            out.close();

            size = new File(fileLocation).length();

        } catch (Exception e) {
            System.out.println("---- Not determine file ----");
            System.out.println(e);
        }

        return size;
    }

    public static void copyFileToDisk(MultipartFile file, String rootDir, String folder, String fileName, String fileExtension) {
        Path path = Paths.get(rootDir.replace("file:", "") + "/" + folder);

        try {
            if (!Files.exists(path))
                Files.createDirectories(path);

            Files.copy(
                    file.getInputStream(),
                    path.resolve(fileName + "." + fileExtension),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            System.out.println("---- Not determine file ----");
            System.out.println(e);
        }
    }
}

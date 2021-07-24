package com.quanglv.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "file")
public class FilesConfig {

//    @Value("${file.templateDirectory}")
    private String templateDirectory;

//    @Value("${file.rootDirectory}")
    private String rootDirectory;

//    @Value("${file.publicDirectory}")
    private String publicDirectory;

//    @Value("${file.privateDirectory}")
    private String privateDirectory;
}

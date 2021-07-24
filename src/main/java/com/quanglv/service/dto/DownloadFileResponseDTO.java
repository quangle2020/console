package com.quanglv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DownloadFileResponseDTO {
    private Resource resource;
    private String fileName;
}

package com.quanglv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSearchResponseDTO {
    private Long total;

    private List<SearchProductsResponseDTO> item;
}

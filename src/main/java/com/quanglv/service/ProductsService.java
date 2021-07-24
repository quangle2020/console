package com.quanglv.service;

import com.quanglv.service.dto.ProductsDTO;
import com.quanglv.service.dto.ProductsSearchRequestDTO;
import com.quanglv.service.dto.ProductsSearchResponseDTO;

import java.util.List;

public interface ProductsService {

    List<ProductsDTO> getAll();

    ProductsDTO save(ProductsDTO request);

    ProductsSearchResponseDTO search(ProductsSearchRequestDTO requestDTO);
}

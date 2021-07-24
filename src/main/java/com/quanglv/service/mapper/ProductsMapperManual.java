package com.quanglv.service.mapper;

import com.quanglv.domain.first.Products;
import com.quanglv.service.dto.SearchProductsResponseDTO;

public class ProductsMapperManual {

    public SearchProductsResponseDTO toSearch(Products entity) {
        if (entity == null) {
            return null;
        }

        SearchProductsResponseDTO productsDTO = new SearchProductsResponseDTO();

        productsDTO.setId(entity.getId());
        productsDTO.setName(entity.getName());
        productsDTO.setCode(entity.getCode());
        productsDTO.setPrice(Long.toString(entity.getPrice().longValue()));
        productsDTO.setUrl(entity.getUrl());
        productsDTO.setPromotion(entity.getPromotion());
        productsDTO.setStatus(entity.getStatus());
        productsDTO.setCreatedUser(entity.getCreatedUser());
        productsDTO.setCreatedDate(entity.getCreatedDate());
        productsDTO.setUpdatedDate(entity.getUpdatedDate());
        productsDTO.setUpdatedUser(entity.getUpdatedUser());

        return productsDTO;
    }
}

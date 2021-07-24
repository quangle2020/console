package com.quanglv.service.mapper;

import com.quanglv.domain.first.Products;
import com.quanglv.service.dto.ProductsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductsMapper extends EntityMapper<ProductsDTO, Products> {
}

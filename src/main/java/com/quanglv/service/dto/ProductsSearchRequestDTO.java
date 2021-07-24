package com.quanglv.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProductsSearchRequestDTO implements Serializable {

    private String name;

    private String code;

    private Integer priceFrom;

    private Integer priceTo;

    private Integer promotion;

    private Long createdUser;

    private String dateFrom;

    private String dateTo;

    private Integer page;

    private Integer size;

    private String order;

    private String sortName;
}

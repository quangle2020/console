package com.quanglv.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

@JsonPropertyOrder({
        "id",
        "name",
        "code",
        "price",
        "promotion",
        "url",
        "status",
        "createdUser",
        "updatedUser",
        "createdDate",
        "updatedDate"
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("url")
    private String url;

    @JsonProperty("promotion")
    private Integer promotion;

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdUser")
    private Long createdUser;

    @JsonProperty("createdDate")
    private Date createdDate;

    @JsonProperty("updatedDate")
    private Date updatedDate;

    @JsonProperty("updatedUser")
    private Long updatedUser;
}

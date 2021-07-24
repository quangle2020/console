package com.quanglv.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.Instant;
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
public class SearchProductsResponseDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("price")
    private String price;

    @JsonProperty("url")
    private String url;

    @JsonProperty("promotion")
    private Integer promotion;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("createdUser")
    private Long createdUser;

    @JsonProperty("createdDate")
    private Instant createdDate;

    @JsonProperty("updatedDate")
    private Instant updatedDate;

    @JsonProperty("updatedUser")
    private Long updatedUser;
}

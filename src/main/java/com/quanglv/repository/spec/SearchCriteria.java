package com.quanglv.repository.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private String value;

    public SearchCriteria(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

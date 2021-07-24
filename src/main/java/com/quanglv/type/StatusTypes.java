package com.quanglv.type;

public enum StatusTypes {
    ACTIVE("ACTIVE"),
    DE_ACTIVE("DEACTIVE");

    StatusTypes(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }
}

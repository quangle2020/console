package com.quanglv.type;

public enum FileTypes {
    PUBLIC("PUBLIC"),
    INTERNAL("INTERNAL");

    FileTypes(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }
}

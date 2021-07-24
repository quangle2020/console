package com.quanglv.type;

public enum  OperationTypes {

    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_THAN_DATE("<date"),
    GREATER_THAN_DATE(">date"),
    EQUAL("="),
    LIKE(":");

    OperationTypes(String code){
        this.code = code;
    }

    private String code;

    public String getCode(){
        return this.code;
    }
}

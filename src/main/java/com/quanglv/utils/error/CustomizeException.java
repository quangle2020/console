package com.quanglv.utils.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomizeException extends RuntimeException{
    private static final String ERROR_NAME = "error";
    private static Map<String, Object> attributes= new LinkedHashMap<>();
    public static final String MESSAGE = "message";
    public static final String ERROR_ITEM = "item";
    public static final String ERROR_CODE = "errorCode";
    public static final String ROW_ID = "rowId";

    public CustomizeException(List<Map<String,Object>> errorItems){
        this.attributes.put(ERROR_NAME,errorItems);
    }

    public CustomizeException(String message, String item, String errorCode){
        Map<String, Object> errorItems = putError(message, item, errorCode, null);
        this.attributes.put(ERROR_NAME,errorItems);
    }

    public CustomizeException(String message, String item, String errorCode, Integer rowId){
        Map<String, Object> errorItems = putError(message, item, errorCode, rowId);
        this.attributes.put(ERROR_NAME,errorItems);
    }


    public static Map<String, Object> putError(String message, String item, String errorCode, Integer rowId){
        Map<String, Object> error = new LinkedHashMap<>();
        error.put(MESSAGE, message);
        error.put(ERROR_ITEM, item);
        error.put(ERROR_CODE, errorCode);
        if(rowId != null){
            error.put(ROW_ID, rowId);
        }
        return error;
    }

    public static Map<String, Object> putError(String message, String item, String errorCode){
        Map<String, Object> error = new LinkedHashMap<>();
        error.put(MESSAGE, message);
        error.put(ERROR_ITEM, item);
        error.put(ERROR_CODE, errorCode);
        return error;
    }

    public Map<String, Object> getExtensions(){
        return this.attributes;
    }
}

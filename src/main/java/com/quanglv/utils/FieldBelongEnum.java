package com.quanglv.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.quanglv.constant.Constants.*;


/**
 * Map to field belong
 * 8 Employee
 * 5 Customer
 * 4 Business cards
 * 6 Activity
 * 14 Product
 * 15 TASK
 * 16 Trading products
 * 1401 Product Breakdown
 */
public enum FieldBelongEnum {
    TENANT(                             24, "Tenant",              MICRO_SERVICE_TENANTS),
    EMPLOYEE(                           8, "Employee",             MICRO_SERVICE_EMPLOYEES),
    CUSTOMER(                           5, "Customer",             MICRO_SERVICE_CUSTOMERS),
    BUSINESS_CARD(                      4, "Business cards",       MICRO_SERVICE_BUSINESS_CARDS),
    ACTIVITY(                           6, "Activity",             MICRO_SERVICE_ACTIVITIES),
    PRODUCT(                            14, "Product",             MICRO_SERVICE_PRODUCTS),
    TASK(                               15, "TASK",                MICRO_SERVICE_SCHEDULES),
    TRADING_PRODUCT(                    16, "Trading products",    MICRO_SERVICE_SALES),
    PRODUCT_BREAKDOWN(                  1401, "Product Breakdown", MICRO_SERVICE_SALES),
    TIMELINE(                           3, "timeline",             MICRO_SERVICE_TIMELINES),
    TIMELINE_GROUP(                     301, "timeline group",     MICRO_SERVICE_TIMELINES),
    MILESTONE(                          1501, "milestone",         MICRO_SERVICE_SCHEDULES),
    SCHEDULE(                           2, "Schedule",             MICRO_SERVICE_SCHEDULES),
    LOG_ACCESS(                         2101, "Log Access",        MICRO_SERVICE_COMMONS),
    PRODUCT_ITEM(                       1401, "product item",      MICRO_SERVICE_SALES);

    private Integer code;
    private String name;
    private String microServiceName;

    private FieldBelongEnum(int code, String name, String microServiceName) {
        this.code = code;
        this.name = name;
        this.microServiceName = microServiceName;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMicroServiceName() {
        return microServiceName;
    }

    public static final FieldBelongEnum getByValue(int value) {
        return Arrays.stream(FieldBelongEnum.values()).filter(e -> e.getCode() == value).findFirst().orElse(EMPLOYEE);
    }
    
    public static List<Integer> getFieldBelongList(){
        List<Integer> fieldBelongList = new ArrayList<>();
        for (FieldBelongEnum fieldBelong : FieldBelongEnum.values()) {
            fieldBelongList.add(fieldBelong.getCode());
        }
        return fieldBelongList;
    }
}

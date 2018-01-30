package com.gather.gathercommons.model;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:29
 */
public enum DataType {
    IMAGE(5),
    STRING(1),
    NUMBER(2),
    PERCENTAGE(3);

    private Integer value;

    DataType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

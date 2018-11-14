package com.gather.gathercommons.model.datatable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/14/18
 * Time: 18:47
 */
public enum ColumnAlignment {
    LEFT(-1),
    RIGHT(1),
    CENTER(0);

    private Integer value;

    ColumnAlignment(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

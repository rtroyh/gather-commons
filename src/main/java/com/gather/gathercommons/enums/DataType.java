package com.gather.gathercommons.enums;

public enum DataType {
    CARACTER(1), ENTERO(2), DECIMAL(3);

    Integer index;

    private DataType(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return this.index;
    }
}

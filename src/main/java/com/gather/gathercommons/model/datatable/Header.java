package com.gather.gathercommons.model.datatable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 27-07-16
 * Time: 11:24
 */
public class Header implements Serializable {
    private DataType dataType;
    private Boolean isVisible;
    private Boolean showZeros;

    private String headerText;
    private Integer decimalPlaces;
    private Double proportion;

    private ColumnAlignment columnAlignment;

    public Header(String headerText,
                  DataType dataType,
                  Integer decimalPlaces,
                  Boolean showZeros,
                  Boolean isVisible,
                  Double proportion,
                  ColumnAlignment columnAlignment) {
        this.dataType = dataType;
        this.isVisible = isVisible;
        this.showZeros = showZeros;
        this.headerText = headerText;
        this.decimalPlaces = decimalPlaces;
        this.proportion = proportion;
        this.columnAlignment = columnAlignment;
    }

    public ColumnAlignment getColumnAlignment() {
        if (columnAlignment == null) {
            columnAlignment = ColumnAlignment.LEFT;
        }

        return columnAlignment;
    }

    public void setColumnAlignment(ColumnAlignment columnAlignment) {
        this.columnAlignment = columnAlignment;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Boolean isVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Boolean getShowZeros() {
        return showZeros;
    }

    public void setShowZeros(Boolean showZeros) {
        this.showZeros = showZeros;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
}

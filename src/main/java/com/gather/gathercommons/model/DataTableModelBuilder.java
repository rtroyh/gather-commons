package com.gather.gathercommons.model;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 15:09
 */
public class DataTableModelBuilder {
    private IDataTableModel model;

    public DataTableModelBuilder createDatatable() {
        this.model = new DefaultDataTableModel();

        return this;
    }

    public DataTableModelBuilder setTitles(Object[] titles) {
        this.model.getTitles().add(Arrays.asList(titles));

        return this;
    }

    public DataTableModelBuilder addColumn(Object[] column) {
        this.model.getHeaders().add(Arrays.asList(column));

        return this;
    }

    public DataTableModelBuilder addColumn(String headerText,
                                           DataType dataType,
                                           Integer decimalPlaces,
                                           Boolean showZeros,
                                           Boolean isVisible,
                                           Double proportion) {
        this.model.getHeaders().add(Arrays.asList(new Object[]{headerText, dataType, decimalPlaces, showZeros, isVisible, proportion}));

        return this;
    }

    public DataTableModelBuilder addRow(Object[] row) {
        this.model.getRows().add(Arrays.asList(row));

        return this;
    }

    public IDataTableModel build() {
        return model;
    }
}

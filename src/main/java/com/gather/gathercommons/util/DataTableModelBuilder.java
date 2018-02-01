package com.gather.gathercommons.util;

import com.gather.gathercommons.model.Column;
import com.gather.gathercommons.model.DataType;
import com.gather.gathercommons.model.DefaultDataTableModel;
import com.gather.gathercommons.model.IDataTableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 15:09
 */
public class DataTableModelBuilder {
    private IDataTableModel model;
    private List<Column> columns;

    private List<Column> getColumns() {
        if (columns == null) {
            columns = new ArrayList<>();
        }

        return columns;
    }

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

        this.getColumns().add(new Column(headerText,
                                         dataType,
                                         decimalPlaces,
                                         showZeros,
                                         isVisible,
                                         proportion));

        this.model.getHeaders().add(Arrays.asList(new Object[]{headerText, dataType.getValue(), decimalPlaces, showZeros ? 1 : 0, isVisible ? 1 : 0, proportion}));

        return this;
    }

    public DataTableModelBuilder addRow(Object[] row) {
        this.model.getRows().add(Arrays.asList(row));

        return this;
    }

    public DataTableModelBuilder addRandomRows(Integer count) {
        for (Integer i = 0; i < count; i++) {

            if (Validator.validateList(this.getColumns())) {
                List<Object> row = new ArrayList<>();

                for (Column column : this.getColumns()) {
                    if (column.isVisible()) {
                        if (column.getDataType().equals(DataType.IMAGE)) {
                            row.add("imagen.png");
                        } else if (column.getDataType().equals(DataType.STRING)) {
                            row.add(UUID.randomUUID().toString().replace("-",
                                                                         " "));
                        } else if (column.getDataType().equals(DataType.NUMBER)) {
                            if (column.getDecimalPlaces().equals(0)) {
                                row.add(ThreadLocalRandom.current().nextInt());
                            } else {
                                row.add(ThreadLocalRandom.current().nextDouble());
                            }
                        } else if (column.getDataType().equals(DataType.PERCENTAGE)) {
                            row.add((double) ThreadLocalRandom.current().nextInt(0,
                                                                                 100) / 100);
                        }
                    }
                }

                this.model.getRows().add(row);
            }
        }

        return this;
    }

    public IDataTableModel build() {
        return model;
    }
}

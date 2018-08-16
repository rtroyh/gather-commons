package com.gather.gathercommons.util;

import com.gather.gathercommons.model.DataType;
import com.gather.gathercommons.model.DefaultDataTableModel;
import com.gather.gathercommons.model.Header;
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
    private List<Header> header;

    private List<Header> getHeader() {
        if (header == null) {
            header = new ArrayList<>();
        }

        return header;
    }

    private DataTableModelBuilder() {
        this.model = new DefaultDataTableModel();
    }

    public static DataTableModelBuilder createDatatable() {
        return new DataTableModelBuilder();
    }

    public DataTableModelBuilder setTitles(Object[] titles) {
        this.model.getTitles().add(Arrays.asList(titles));

        return this;
    }

    public DataTableModelBuilder addColumn(String headerText,
                                           DataType dataType,
                                           Integer decimalPlaces,
                                           Boolean showZeros,
                                           Boolean isVisible,
                                           Double proportion) {
        this.getHeader().add(new Header(headerText,
                                        dataType,
                                        decimalPlaces,
                                        showZeros,
                                        isVisible,
                                        proportion));

        return this;
    }

    public DataTableModelBuilder addRow(Object[] row) {
        this.model.getRows().add(Arrays.asList(row));

        return this;
    }

    public DataTableModelBuilder addRow(List<Object> row) {
        this.model.getRows().add(row);

        return this;
    }

    public DataTableModelBuilder addRows(List<List<Object>> rows) {
        for (List<Object> row : rows) {
            this.model.getRows().add(row);
        }

        return this;
    }

    public DataTableModelBuilder addRandomRows(Integer count) {
        for (Integer i = 0; i < count; i++) {

            if (Validator.validateList(this.getHeader())) {
                List<Object> row = new ArrayList<>();

                for (Header header : this.getHeader()) {
                    if (header.isVisible()) {
                        if (header.getDataType().equals(DataType.IMAGE)) {
                            row.add("imagen.png");
                        } else if (header.getDataType().equals(DataType.STRING)) {
                            row.add(UUID.randomUUID().toString().replace("-",
                                                                         " "));
                        } else if (header.getDataType().equals(DataType.NUMBER)) {
                            if (header.getDecimalPlaces().equals(0)) {
                                row.add(ThreadLocalRandom.current().nextInt());
                            } else {
                                row.add(ThreadLocalRandom.current().nextDouble());
                            }
                        } else if (header.getDataType().equals(DataType.PERCENTAGE)) {
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

    public IDataTableModel build() throws
                                   Exception {
        if (Validator.validateList(this.getHeader())) {
            for (Header header : this.getHeader()) {
                Object[] data = {header.getHeaderText(),
                                 header.getDataType().getValue(),
                                 header.getDecimalPlaces(),
                                 header.getShowZeros() ? 1 : 0,
                                 header.isVisible() ? 1 : 0,
                                 header.getProportion()};

                this.model.getHeaders().add(Arrays.asList(data));
            }
        } else {
            throw new Exception("NO SE HAN DEFINIDO LAS COLUMNAS");
        }

        return model;
    }
}

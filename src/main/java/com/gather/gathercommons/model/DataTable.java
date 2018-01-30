package com.gather.gathercommons.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 11:02
 */
public class DataTable implements Serializable {
    private IDataTableModel dataTableModel;

    private List<Column> columnList;
    private List<Row> rowList;

    public DataTable(IDataTableModel dataTableModel) {
        this.dataTableModel = dataTableModel;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public void setRowList(List<Row> rowList) {
        this.rowList = rowList;
    }
}

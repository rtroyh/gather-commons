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
public class DataTable<E> implements Serializable {
    private IDataTableModel dataTableModel;

    private List<Object> titles;
    private List<Header> headerMetadata;
    private List<E> rows;

    public DataTable(IDataTableModel dataTableModel) {
        this.dataTableModel = dataTableModel;
    }

    public List<Header> getHeaderMetadata() {

        return headerMetadata;
    }

    public void setHeaderMetadata(List<Header> headerMetadata) {
        this.headerMetadata = headerMetadata;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}

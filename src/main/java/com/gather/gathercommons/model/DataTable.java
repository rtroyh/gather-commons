package com.gather.gathercommons.model;

import com.gather.gathercommons.util.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 11:02
 */
public class DataTable<E> implements IDataTable,
                                     Serializable {
    private IDataTableModel dataTableModel;

    private List<Object> titles;
    private List<Header> headers;
    private List<E> rows;

    public DataTable(IDataTableModel dataTableModel) {
        this.dataTableModel = dataTableModel;
    }

    public List<Object> getTitles() {
        if (titles == null) {
            titles = new ArrayList<>();
        }

        return titles;
    }

    public void setTitles(List<Object> titles) {
        this.titles = titles;
    }

    public List<Header> getHeaders() {
        if (headers == null) {
            headers = new ArrayList<>();
        }

        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<E> getRows() {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        return rows;
    }

    @Override
    public Boolean isEmpty() {
        return !Validator.validateList(this.rows);
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}

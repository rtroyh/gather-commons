package com.gather.gathercommons.model.datatable;

import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 11:02
 */
public class DataTable<E extends IListHolder> implements IDataTable<E> {
    private IDataTableModel dataTableModel;

    private IDomainObjectMapper<E> businessObjectMapper;
    private IHeaderBuilder headerBuilder;
    private List<Object> titles;
    private List<Header> headers;
    private List<E> rows;

    public DataTable(IDataTableModel dataTableModel,
                     IDomainObjectMapper<E> businessObjectMapper) {
        this.dataTableModel = dataTableModel;
        this.headerBuilder = new DefaultHeaderBuilder();
        this.businessObjectMapper = businessObjectMapper;

        this.build();
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

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public Boolean isEmpty() {
        return !Validator.validateList(this.rows);
    }

    private void build() {
        if (this.businessObjectMapper == null) {
            throw new NullPointerException("NO EXISTE UN MAPPER");
        }

        if (Validator.validateList(this.dataTableModel.getTitles())) {
            this.setTitles(dataTableModel.getTitles().get(0));
        }

        for (List<Object> header : dataTableModel.getHeaders()) {
            this.getHeaders().add(headerBuilder.createHeader(header));
        }

        for (List<Object> row : dataTableModel.getRows()) {
            this.getRows().add(this.businessObjectMapper.createObject(row));
        }
    }

}

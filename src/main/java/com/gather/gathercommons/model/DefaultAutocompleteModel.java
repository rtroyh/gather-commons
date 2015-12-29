package com.gather.gathercommons.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 11/15/12
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultAutocompleteModel implements IAutocompleteModel,
                                                 Serializable {
    private Object selectedObject;

    private List<List<Object>> titles;
    private List<List<Object>> headers;
    private List<List<Object>> rows;

    @Override
    public List<List<Object>> getHeaders() {
        if (this.headers == null) {
            this.headers = new ArrayList<List<Object>>();
        }

        return headers;
    }

    @Override
    public List<List<Object>> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<List<Object>>();
        }

        return rows;
    }

    @Override
    public List<List<Object>> getTitles() {
        if (this.titles == null) {
            this.titles = new ArrayList<List<Object>>();
        }

        return titles;
    }

    @Override
    public void setTitles(List<List<Object>> titles) {
        this.titles = titles;
    }

    @Override
    public void setHeaders(List<List<Object>> headers) {
        this.headers = headers;
    }

    @Override
    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    @Override
    public Object getSelectedObject() {
        return this.selectedObject;
    }

    @Override
    public void setSelectedObject(Object selectedObject) {
        this.selectedObject = selectedObject;
    }
}

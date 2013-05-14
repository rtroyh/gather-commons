package com.gather.gathercommons.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultListModel implements IListModel {
    private List<List<Object>> rows;

    @Override
    public List<List<Object>> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<List<Object>>();
        }

        return rows;
    }

    @Override
    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }
}

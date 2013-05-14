package com.gather.gathercommons.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IListModel {
    public List<List<Object>> getRows();

    public void setRows(List<List<Object>> rows);
}

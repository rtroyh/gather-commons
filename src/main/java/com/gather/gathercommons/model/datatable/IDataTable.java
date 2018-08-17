package com.gather.gathercommons.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 15:17
 */
public interface IDataTable<E extends IListHolder> {
    List<Object> getTitles();

    void setTitles(List<Object> titles);

    List<Header> getHeaders();

    void setHeaders(List<Header> headers);

    List<E> getRows();

    void setRows(List<E> rows);

    Boolean isEmpty();
}

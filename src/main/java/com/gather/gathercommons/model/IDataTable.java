package com.gather.gathercommons.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 15:17
 */
public interface IDataTable<E> {
    List<Object> getTitles();

    List<Header> getHeaders();

    List<E> getRows();

    Boolean isEmpty();
}

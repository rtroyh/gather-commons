package com.gather.gathercommons.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:32
 */
public interface IHeaderBuilder {
    Header createHeader(List<Object> list);
}

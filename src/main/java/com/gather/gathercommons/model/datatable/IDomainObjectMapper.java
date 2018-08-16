package com.gather.gathercommons.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:26
 */
public interface IDomainObjectMapper<E> {
    E createObject(List<Object> list);
}

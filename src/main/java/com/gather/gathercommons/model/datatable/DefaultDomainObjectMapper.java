package com.gather.gathercommons.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:52
 */
public class DefaultDomainObjectMapper implements IDomainObjectMapper<List<Object>> {
    @Override
    public List<Object> createObject(List<Object> list) {
        return list;
    }
}

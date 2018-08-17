package com.gather.gathercommons.model.datatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/17/18
 * Time: 13:49
 */
public class DefaultListHolder implements IListHolder {
    private List<Object> objects;

    public DefaultListHolder(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public List<Object> getList() {
        if (objects == null) {
            objects = new ArrayList<>();
        }

        return this.objects;
    }
}

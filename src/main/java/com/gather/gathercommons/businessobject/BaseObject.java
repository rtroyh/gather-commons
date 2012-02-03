package com.gather.gathercommons.businessobject;

public abstract class BaseObject implements IBaseObject {

    protected Boolean selected = Boolean.FALSE;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

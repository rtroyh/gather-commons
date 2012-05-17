package com.gather.gathercommons.businessobject;

public class Comuna {
    private ID id;
    private String comuna;

    public ID getId() {
        if (id == null) {
            id = new ID();
        }

        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getComuna() {
        if (comuna != null) {
            return comuna.trim();
        }
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

}

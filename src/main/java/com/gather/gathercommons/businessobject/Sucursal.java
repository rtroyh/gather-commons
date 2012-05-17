package com.gather.gathercommons.businessobject;

public class Sucursal {
    private ID id;
    private ID tipo;

    public ID getId() {
        if (id == null) {
            id = new ID();
        }

        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getTipo() {
        if (tipo == null) {
            tipo = new ID();
        }

        return tipo;
    }

    public void setTipo(ID tipo) {
        this.tipo = tipo;
    }

}

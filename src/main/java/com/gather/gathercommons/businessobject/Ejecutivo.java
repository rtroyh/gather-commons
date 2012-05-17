package com.gather.gathercommons.businessobject;

public class Ejecutivo extends Persona {
    private ID id;
    private ID tipo;

    private Sucursal sucursal;

    public Sucursal getSucursal() {
        if (sucursal == null) {
            sucursal = new Sucursal();
        }

        return sucursal;
    }

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

package com.gather.gathercommons.businessobject;

public class Cliente extends Persona {
    private ID id;
    private Double aporte;
    private Double rescate;

    private Ejecutivo ejecutivo;

    public ID getId() {
        if (id == null) {
            id = new ID();
        }
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Double getAporte() {
        return aporte;
    }

    public void setAporte(Double aporte) {
        this.aporte = aporte;
    }

    public Double getRescate() {
        return rescate;
    }

    public void setRescate(Double rescate) {
        this.rescate = rescate;
    }

    public Ejecutivo getEjecutivo() {
        if (ejecutivo == null) {
            ejecutivo = new Ejecutivo();
        }
        return ejecutivo;
    }

    public void setEjecutivo(Ejecutivo ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

}

package com.gather.gathercommons.businessobject;

public class BarraMenu extends BaseObject {
    private Integer nivel;
    private String descripcion;
    private String ayuda;
    private String ruta;
    private Integer tipo;

    public BarraMenu() {
        super();
    }

    public BarraMenu(Integer nivel,
                     String descripcion,
                     String ayuda,
                     String ruta,
                     Integer tipo) {
        super();
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.ayuda = ayuda;
        this.ruta = ruta;
        this.tipo = tipo;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAyuda() {
        return this.ayuda;
    }

    public void setAyuda(String ayuda) {
        this.ayuda = ayuda;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getTipo() {
        return this.tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return descripcion;
    }
}

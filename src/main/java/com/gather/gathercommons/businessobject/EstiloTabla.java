package com.gather.gathercommons.businessobject;

public class EstiloTabla {
    private Integer color;
    private Integer letra;
    private Integer indentacion;
    private Integer salto;

    public EstiloTabla(Integer color, Integer letra, Integer indentacion, Integer salto) {
        super();
        this.color = color;
        this.letra = letra;
        this.indentacion = indentacion;
        this.salto = salto;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getLetra() {
        return letra;
    }

    public void setLetra(Integer letra) {
        this.letra = letra;
    }

    public Integer getIndentacion() {
        return indentacion;
    }

    public void setIndentacion(Integer indentacion) {
        this.indentacion = indentacion;
    }

    public Integer getSalto() {
        return salto;
    }

    public void setSalto(Integer salto) {
        this.salto = salto;
    }

}

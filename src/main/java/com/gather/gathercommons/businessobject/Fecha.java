package com.gather.gathercommons.businessobject;

import java.util.Date;

public class Fecha {
    private Date fechaComoDate;
    private String fechaComoTexto;

    public Fecha() {
        super();
    }

    public Fecha(Date fechaComoDate) {
        super();
        this.fechaComoDate = fechaComoDate;
    }

    public Fecha(String fechaComoTexto) {
        super();
        this.fechaComoTexto = fechaComoTexto;
    }

    public Fecha(Date fechaComoDate, String fechaComoTexto) {
        super();
        this.fechaComoDate = fechaComoDate;
        this.fechaComoTexto = fechaComoTexto;
    }

    public Date getFechaComoDate() {
        return fechaComoDate;
    }

    public void setFechaComoDate(Date fechaComoDate) {
        this.fechaComoDate = fechaComoDate;
    }

    public String getFechaComoTexto() {
        return fechaComoTexto;
    }

    public void setFechaComoTexto(String fechaComoTexto) {
        this.fechaComoTexto = fechaComoTexto;
    }

}

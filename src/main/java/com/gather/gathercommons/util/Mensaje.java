package com.gather.gathercommons.util;

public class Mensaje {
    private String estiloError = "color: red;";
    private String estiloOK = "color: green;";
    private String mensaje;

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }

    public String getEstiloOK() {
        return estiloOK;
    }

    public void setEstiloOK(String estiloOK) {
        this.estiloOK = estiloOK;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

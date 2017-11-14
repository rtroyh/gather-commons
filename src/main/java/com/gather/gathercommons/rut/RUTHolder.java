package com.gather.gathercommons.rut;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/14/17
 * Time: 12:49
 */
public class RUTHolder {
    private String rutOrigen;
    private String rutSinDV;
    private String rutConDV;
    private String digitoVerificador;

    private Boolean origenTieneDV;
    private Boolean dvEsVerificable;


    public RUTHolder(String rutOrigen) {
        this.rutOrigen = rutOrigen;
    }

    public String getRutSinDV() {
        return rutSinDV;
    }

    public void setRutSinDV(String rutSinDV) {
        this.rutSinDV = rutSinDV;
    }

    public String getRutConDV() {
        return rutConDV;
    }

    public void setRutConDV(String rutConDV) {
        this.rutConDV = rutConDV;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }
}

package com.gather.gathercommons.rut;

import com.gather.gathercommons.util.DigitoVerificador;
import com.gather.gathercommons.util.Validator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/14/17
 * Time: 12:49
 * Clase para manipular campos RUT que vienen desde el origen con DV.
 */
public class RUTHolder {
    private String rutSinDV;
    private String rutConDV;
    private String digitoVerificador;

    private Boolean origenTieneDV;
    private Boolean dvEsVerificable;

    public RUTHolder(String rutConDV) throws
                                      RUTInvalidoException {
        if (Validator.validateRUT(rutConDV)) {
            this.rutConDV = rutConDV;
            this.rutSinDV = rutConDV.substring(0,
                                               rutConDV.indexOf("-")).replaceAll("\\.",
                                                                                 "");
            this.digitoVerificador = rutConDV.substring(rutConDV.indexOf("-") + 1,
                                                        rutConDV.indexOf("-") + 2).toUpperCase();
        } else {
            throw new RUTInvalidoException("RUT ES INVALIDO");
        }
    }

    public String getDigitoVerificadorCalculado() {
        return DigitoVerificador.calcular(Integer.parseInt(this.getRutSinDV()));
    }

    public Boolean esValido() {
        Boolean rutEsValido;

        if (Validator.validateRUT(this.rutConDV)) {
            rutEsValido = StringUtils.isNumeric(rutSinDV);

            if (!DigitoVerificador.calcular(Integer.parseInt(rutSinDV)).equals(digitoVerificador)) {
                rutEsValido = false;
            }
        } else {
            rutEsValido = true;
        }

        return rutEsValido;
    }

    public String getRutSinDV() {
        if (rutSinDV == null) {

        }

        return rutSinDV;
    }

    public String getRutConDV() {
        if (rutConDV == null) {

        }

        return rutConDV;
    }

    public String getDigitoVerificador() {
        if (digitoVerificador == null) {

        }

        return digitoVerificador;
    }
}

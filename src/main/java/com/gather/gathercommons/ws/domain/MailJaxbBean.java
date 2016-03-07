package com.gather.gathercommons.ws.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 07-03-16
 * Time: 13:32
 */
@XmlRootElement
public class MailJaxbBean {
    private Integer tipo;
    private String resultado;

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

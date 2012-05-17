package com.gather.gathercommons.businessobject;

public class Direccion {
    private String calle;
    private String numero;
    private String departamento;
    private String poblacion;
    private Comuna comuna;
    private String codigoPostal;
    private String region;
    private String ciudad;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        if (calle != null) {
            return calle.trim();
        }

        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        if (numero != null) {
            return numero.trim();
        }
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        if (departamento != null) {
            return departamento.trim();
        }

        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPoblacion() {
        if (poblacion != null) {
            return poblacion.trim();
        }

        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Comuna getComuna() {
        if (comuna == null) {
            comuna = new Comuna();
        }

        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public String getCodigoPostal() {
        if (codigoPostal != null) {
            return codigoPostal.trim();
        }
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getRegion() {
        if (region != null) {
            return region.trim();
        }
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}

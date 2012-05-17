package com.gather.gathercommons.businessobject;


public class Persona extends BaseObject {
    private String rut;
    private String cic;
    private String dv;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String celular;
    private String fax;
    private String email;
    private Fecha fechaNacimiento;
    private ID sexo;
    private ID estadoCivil;
    private Double rentaImponible;
    private String nacionalidad;

    private Direccion direccion;

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Direccion getDireccion() {
        if (direccion == null) {
            direccion = new Direccion();
        }

        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDv() {
        if (dv != null) {
            return dv.trim();
        }

        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNombre() {
        if (nombre != null) {
            return nombre.trim();
        }

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        if (telefono != null) {
            return telefono.trim();
        }

        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        if (fax != null) {
            return fax.trim();
        }

        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        if (email != null) {
            return email.trim();
        }

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        if (celular != null) {
            return celular.trim();
        }

        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Fecha getFechaNacimiento() {
        if (fechaNacimiento == null) {
            fechaNacimiento = new Fecha();
        }

        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ID getSexo() {
        if (sexo == null) {
            sexo = new ID();
        }

        return sexo;
    }

    public void setSexo(ID sexo) {
        this.sexo = sexo;
    }

    public ID getEstadoCivil() {
        if (estadoCivil == null) {
            estadoCivil = new ID();
        }
        return estadoCivil;
    }

    public void setEstadoCivil(ID estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Double getRentaImponible() {
        return rentaImponible;
    }

    public void setRentaImponible(Double rentaImponible) {
        this.rentaImponible = rentaImponible;
    }

    public String getCic() {
        if (cic != null) {
            return cic.trim();
        }

        return cic;
    }

    public void setCic(String cic) {
        this.cic = cic;
    }

    public String getApellidoPaterno() {
        if (apellidoPaterno != null) {
            return apellidoPaterno.trim();
        }

        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        if (apellidoMaterno != null) {
            return apellidoMaterno.trim();
        }

        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

}

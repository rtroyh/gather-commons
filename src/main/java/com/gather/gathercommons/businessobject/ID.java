package com.gather.gathercommons.businessobject;

public class ID {

	private Integer clave;
	private String codigo;
	private String descripcion;
	protected Boolean selected = Boolean.FALSE;

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public ID() {
		super();
	}

	public ID(	Integer clave,
				String descripcion) {
		this();
		this.clave = clave;
		this.descripcion = descripcion;
	}

	public ID(	String codigo,
				String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public ID(	Integer clave,
				String codigo,
				String descripcion) {
		this(	clave,
				descripcion);
		this.codigo = codigo;
	}

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		if (descripcion != null) {
			return descripcion.trim();
		} else {
			return " ";
		}
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		if (codigo == null) {
			return " ";
		} else {
			return codigo.trim();
		}
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.getDescripcion();
	}

}

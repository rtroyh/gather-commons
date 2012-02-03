package com.gather.gathercommons.businessobject;

public class NodoMenu extends BarraMenu {

	private Integer c1;
	private Integer c2;
	private Integer c3;
	private Integer c4;

	public NodoMenu(Integer nivel,
					String descripcion,
					String ayuda,
					String ruta,
					Integer tipo) {
		super(	nivel,
				descripcion,
				ayuda,
				ruta,
				tipo);
	}

	public NodoMenu(Integer nivel,
					String descripcion,
					String ayuda,
					String ruta,
					Integer tipo,
					Integer c1,
					Integer c2,
					Integer c3,
					Integer c4) {
		this(	nivel,
				descripcion,
				ayuda,
				ruta,
				tipo);

		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
	}

	public Integer getC1() {
		return c1;
	}

	public void setC1(Integer c1) {
		this.c1 = c1;
	}

	public Integer getC2() {
		return c2;
	}

	public void setC2(Integer c2) {
		this.c2 = c2;
	}

	public Integer getC3() {
		return c3;
	}

	public void setC3(Integer c3) {
		this.c3 = c3;
	}

	public Integer getC4() {
		return c4;
	}

	public void setC4(Integer c4) {
		this.c4 = c4;
	}

}

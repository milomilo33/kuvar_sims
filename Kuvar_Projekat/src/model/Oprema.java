package model;

import java.io.Serializable;

public class Oprema implements Serializable {
	private Integer sifraOpreme;
	private String naziv;

	public Oprema() {
	}

	public Oprema(Integer sifraOpreme, String naziv) {
		this.sifraOpreme = sifraOpreme;
		this.naziv = naziv;
	}

	public Integer getSifraOpreme() {
		return sifraOpreme;
	}

	public void setSifraOpreme(Integer sifraOpreme) {
		this.sifraOpreme = sifraOpreme;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}

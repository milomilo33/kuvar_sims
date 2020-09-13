package model;

import java.io.Serializable;

public class Namirnica implements Serializable {
	private Integer sifraNamirnice;
	private String naziv;

	public Namirnica() {

	}

	public Namirnica(Integer sifraNamirnice, String naziv) {
		this.sifraNamirnice = sifraNamirnice;
		this.naziv = naziv;
	}

	public Integer getSifraNamirnice() {
		return sifraNamirnice;
	}

	public void setSifraNamirnice(Integer sifraNamirnice) {
		this.sifraNamirnice = sifraNamirnice;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return naziv;
	}
}

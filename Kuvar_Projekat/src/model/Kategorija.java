package model;

import java.io.Serializable;
import java.util.List;

public class Kategorija implements Serializable {
	private Integer sifraKategorije;
	private String naziv;
	private List<Kategorija> potkategorije;

	public Kategorija() {
	}

	public Kategorija(Integer sifraKategorije, String naziv, List<Kategorija> potkategorije) {
		this.sifraKategorije = sifraKategorije;
		this.naziv = naziv;
		this.potkategorije = potkategorije;
	}

	public Integer getSifraKategorije() {
		return sifraKategorije;
	}
	public void setSifraKategorije(Integer sifraKategorije) {
		this.sifraKategorije = sifraKategorije;
	}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Kategorija> getPotkategorije() {
		return potkategorije;
	}

	public void setPotkategorije(List<Kategorija> potkategorije) {
		this.potkategorije = potkategorije;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Kategorija))
			return false;
		Kategorija c = (Kategorija) o;
		return naziv.equals(c.getNaziv()) && sifraKategorije.equals(c.getSifraKategorije());
	}

	public String toString() {
		return naziv;
	}
}

package model;

import java.util.List;

public class Kategorija {
	private Integer sifraKategorije;
	private String naziv;
	private List<Kategorija> potkategorije;
	public Kategorija() {}
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
	
}

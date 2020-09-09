package model;

import java.util.Date;

public class Komentar {
	private String tekst;
	private Date datum;
	private Korisnik autor;
	private Integer ocena;
	public Komentar() {}
	public Komentar(String tekst, Date datum, Korisnik autor, Integer ocena) {
		this.tekst = tekst;
		this.datum = datum;
		this.autor = autor;
		this.ocena = ocena;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Korisnik getAutor() {
		return autor;
	}
	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	public Integer getOcena() {
		return ocena;
	}
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	
}

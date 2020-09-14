package controller;

import java.util.Date;

import model.Aplikacija;
import model.Komentar;
import model.Korisnik;
import model.Recept;

public class KontrolerProzoraKomentarisanjaRecepta {
	private Aplikacija aplikacija;
	public KontrolerProzoraKomentarisanjaRecepta() {}
	public KontrolerProzoraKomentarisanjaRecepta(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
	}
	public void dodajKomentar(String tekst, Date datum, Korisnik autor ,Integer ocena, Recept r, StringBuilder srOcena) {
		if(tekst.equals("") || tekst == null)
			throw new NullPointerException("Nema teksta komentara");
		else
			aplikacija.menadzerRecepata.dodajKomentar(tekst, datum, autor, ocena, r, srOcena);
	}
}

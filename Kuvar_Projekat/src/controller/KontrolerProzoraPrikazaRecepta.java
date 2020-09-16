package controller;

import java.util.concurrent.atomic.AtomicBoolean;

import model.Aplikacija;
import model.Korisnik;
import model.Recept;

public class KontrolerProzoraPrikazaRecepta {
	private Aplikacija aplikacija;
	
	public KontrolerProzoraPrikazaRecepta() {}
	public KontrolerProzoraPrikazaRecepta(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
	}
	public void pretplatiSe(Korisnik k, AtomicBoolean retVal) {
		aplikacija.menadzerKorisnika.pretplatiSe(k, retVal);
	}
	public boolean verifikacijaRecepta(Recept recept) {
		return aplikacija.menadzerRecepata.verifikacijaRecepta(recept);
	}
}
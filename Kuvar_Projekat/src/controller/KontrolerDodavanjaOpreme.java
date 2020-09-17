package controller;

import model.Aplikacija;
import utility.KorisneMetode;

public class KontrolerDodavanjaOpreme {
	private Aplikacija aplikacija;
	
	public KontrolerDodavanjaOpreme(Aplikacija app) {
		this.aplikacija = app;
	}
	
	public void dodajOpremu(String naziv) {
		if (!KorisneMetode.isNotAnEmptyOrNullString(naziv))
			throw new IllegalArgumentException("Nevalidan naziv!");
		aplikacija.getMenadzerOpreme().dodajOpremu(naziv);
	}
}
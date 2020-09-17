package controller;

import model.Aplikacija;
import utility.KorisneMetode;

public class KontrolerDodavanjaNamirnice {
	private Aplikacija aplikacija;
	
	public KontrolerDodavanjaNamirnice(Aplikacija app) {
		this.aplikacija = app;
	}
	
	public void dodajNamirnicu(String naziv) {
		if (!KorisneMetode.isNotAnEmptyOrNullString(naziv))
			throw new IllegalArgumentException("Nevalidan naziv!");
		aplikacija.getMenadzerNamirnica().dodajNamirnicu(naziv);
	}
}

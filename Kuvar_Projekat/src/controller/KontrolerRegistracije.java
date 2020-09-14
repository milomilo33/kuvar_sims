package controller;

import java.time.LocalDate;

import model.Aplikacija;

public class KontrolerRegistracije {
	private Aplikacija aplikacija;
	
	public KontrolerRegistracije(Aplikacija app) {
		aplikacija = app;
	}
	
	public void dodajNovogKorisnika(String ime, String prezime, LocalDate datumRodjenja, String username,
									String password, String brojTelefona, String adresa) {
		aplikacija.getMenadzerKorisnika().dodajNovogKorisnika(ime, prezime, datumRodjenja, username, password, brojTelefona, adresa);
	}
}

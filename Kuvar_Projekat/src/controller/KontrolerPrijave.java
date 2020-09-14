package controller;

import model.Aplikacija;

public class KontrolerPrijave {
	private Aplikacija aplikacija;
	
	public KontrolerPrijave(Aplikacija app) {
		this.aplikacija = app;
	}
	
	public void prijaviKorisnika(String username, String password) {
		aplikacija.getMenadzerKorisnika().prijaviKorisnika(username, password);
	}
}

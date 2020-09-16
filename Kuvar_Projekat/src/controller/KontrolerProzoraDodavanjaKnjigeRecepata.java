package controller;

import java.util.List;
import java.util.Map;

import model.Aplikacija;
import model.Recept;

public class KontrolerProzoraDodavanjaKnjigeRecepata {
	
	private Aplikacija aplikacija;
	
	public KontrolerProzoraDodavanjaKnjigeRecepata() {}
	
	public KontrolerProzoraDodavanjaKnjigeRecepata(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
	}
	
	public void dodajKnjiguRecepata(String naziv, Map<String, List<Recept>> sekcije) {
		if(naziv.equals("") || naziv == null || sekcije == null || sekcije.isEmpty()) {
			System.out.println("usao");
			throw new NullPointerException();
		}
		else
			this.aplikacija.menadzerKorisnika.dodajKnjiguRecepata(naziv, sekcije);
	} 
}

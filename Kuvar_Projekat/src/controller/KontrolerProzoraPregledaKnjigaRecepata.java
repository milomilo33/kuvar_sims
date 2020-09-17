package controller;

import model.Aplikacija;
import model.KnjigaRecepata;

public class KontrolerProzoraPregledaKnjigaRecepata {
	
	private Aplikacija aplikacija;
	
	public KontrolerProzoraPregledaKnjigaRecepata() {}
	public KontrolerProzoraPregledaKnjigaRecepata(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
	}
	
	public void obrisiKnjiguRecepata(KnjigaRecepata knjigaRecepata) {
		this.aplikacija.menadzerKorisnika.obrisiKnjiguRecepata(knjigaRecepata);
	}
	
	public void preimenujKnjiguRecepata(String naziv, KnjigaRecepata knjigaRecepata) {
		if(naziv.equals("") || naziv == null)
			throw new IllegalArgumentException();
		else
			this.aplikacija.menadzerKorisnika.preimenujKnjiguRecepata(naziv, knjigaRecepata);
	}
	
}

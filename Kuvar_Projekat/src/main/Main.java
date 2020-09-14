package main;

import controller.KontrolerGlavnogProzora;
import model.Aplikacija;
import utility.DataGenerator;
import utility.IDGenerator;
import view.GlavniProzor;

import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File f = new File("data");
		if(!f.exists())
			f.mkdir();
		IDGenerator.INSTANCE.deserialize();
		Aplikacija aplikacija = new Aplikacija();
		//DataGenerator.generisiPodatke(aplikacija);
		aplikacija.setTrenutniKorisnik(aplikacija.menadzerKorisnika.getKorisnici().get(0));
		KontrolerGlavnogProzora kontroler = new KontrolerGlavnogProzora(aplikacija);
		GlavniProzor prozor = new GlavniProzor(aplikacija, kontroler);
		prozor.setVisible(true);
		aplikacija.sacuvajStanje();
		IDGenerator.INSTANCE.serialize();
	}
}



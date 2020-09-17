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
		if (!f.exists())
			f.mkdir();
		IDGenerator.INSTANCE.deserialize();
		Aplikacija aplikacija = new Aplikacija();
		//DataGenerator.generisiPodatke(aplikacija);
		aplikacija.setTrenutniKorisnik(aplikacija.getMenadzerKorisnika().getKorisnici().get(0));
		//aplikacija.getTrenutniKorisnik().getOprema().add(aplikacija.getMenadzerOpreme().getOprema().get(0));
		//aplikacija.getTrenutniKorisnik().getNamirnice().put(aplikacija.getMenadzerNamirnica().getNamirnice().get(0), new Posedovanje((float) 20, MernaJedinica.G));
		KontrolerGlavnogProzora kontroler = new KontrolerGlavnogProzora(aplikacija);
		GlavniProzor prozor = new GlavniProzor(aplikacija, kontroler);
		prozor.setVisible(true);
		//DataGenerator.generisiPodatke(aplikacija);
		aplikacija.sacuvajStanje();
		IDGenerator.INSTANCE.serialize();
	}
}



package main;

import controller.KontrolerGlavnogProzora;
import model.Aplikacija;
import utility.IDGenerator;
import view.GlavniProzor;

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IDGenerator.INSTANCE.deserialize();
		Aplikacija aplikacija = new Aplikacija();
		KontrolerGlavnogProzora kontroler = new KontrolerGlavnogProzora(aplikacija);
		GlavniProzor prozor = new GlavniProzor(aplikacija, kontroler);
		prozor.setVisible(true);
		aplikacija.sacuvajStanje();
		IDGenerator.INSTANCE.serialize();
	}
}



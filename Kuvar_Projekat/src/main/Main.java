package main;

import model.Aplikacija;
import utility.IDGenerator;

import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IDGenerator.INSTANCE.deserialize();
		Aplikacija aplikacija = new Aplikacija();


		aplikacija.sacuvajStanje();
		IDGenerator.INSTANCE.serialize();

	}

}

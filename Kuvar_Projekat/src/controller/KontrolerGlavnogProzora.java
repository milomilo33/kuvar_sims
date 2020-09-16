package controller;

import model.*;

import java.util.List;

public class KontrolerGlavnogProzora {
	private Aplikacija aplikacija;
	
	public KontrolerGlavnogProzora() {}
	public KontrolerGlavnogProzora(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
	}

	public void pretraziRecepte(List<Recept> rezultatiPretrage, String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, String vremePripreme) {
			float vp = 1000000;
			if(!vremePripreme.equals(""))
				vp = Float.parseFloat(vremePripreme);
		    aplikacija.menadzerRecepata.pretraziRecepte(rezultatiPretrage, naziv, kategorije, namirnice, tezina, oprema, vp);
	}
}

package controller;

import model.*;

import java.util.ArrayList;

public class KontrolerProzoraDodavanjaRecepta {
    private Aplikacija aplikacija;

    public KontrolerProzoraDodavanjaRecepta(Aplikacija aplikacija) {
        this.aplikacija = aplikacija;
    }

    public void dodajRecept(String naziv, String vremePripreme, String tezina, ArrayList<Namirnica> namirnice,
                            ArrayList<MernaJedinica> merneJedinice, ArrayList<Float> kolicine, ArrayList<Oprema> oprema, ArrayList<Kategorija> kategorije, String opis) {
        if (naziv.equals("")) throw new NullPointerException();
        if (vremePripreme.equals("")) throw new NullPointerException();
        if (!vremePripreme.matches("[0-9]*.?[0-9]+")) throw new IllegalArgumentException();
        if (namirnice.isEmpty()) throw new NullPointerException();
        Tezina t = Tezina.LAKO;
        if (tezina.equals("Srednje")) t = Tezina.SREDNJE;
        if (tezina.equals("Tesko")) t = Tezina.TESKO;
        aplikacija.getMenadzerRecepata().dodajNoviRecept(naziv, t, opis, Float.parseFloat(vremePripreme), kategorije, namirnice, kolicine, merneJedinice, oprema);
    }
}

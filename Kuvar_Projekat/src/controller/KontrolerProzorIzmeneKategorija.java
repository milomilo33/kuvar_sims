package controller;

import model.Aplikacija;
import model.Kategorija;

public class KontrolerProzorIzmeneKategorija {
    private Aplikacija aplikacija;

    public KontrolerProzorIzmeneKategorija(Aplikacija aplikacija) {
        this.aplikacija = aplikacija;
    }

    public void dodajKategoriju(Kategorija parent, String naziv) {
        if (naziv.equals("")) throw new NullPointerException();
        aplikacija.getMenadzerKategorija().dodajKategoriju(parent, naziv);
    }

    public void obrisiKategoriju(Kategorija kategorija) {
        aplikacija.getMenadzerKategorija().obrisiKategoriju(kategorija);
    }

    public void preimenujKategoriju(Kategorija kategorija, String naziv) {
        if (naziv.equals("")) throw new NullPointerException();
        aplikacija.getMenadzerKategorija().preimenujKategoriju(kategorija, naziv);

    }
}

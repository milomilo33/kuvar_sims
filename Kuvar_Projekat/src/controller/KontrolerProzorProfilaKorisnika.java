package controller;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class KontrolerProzorProfilaKorisnika {
    private Aplikacija aplikacija;

    public KontrolerProzorProfilaKorisnika(Aplikacija aplikacija) {
        this.aplikacija = aplikacija;
    }

    public void izmeniProfil(Korisnik korisnik, String ime, String prezime, String password, String brojTelefona, String adresa, ArrayList<Oprema> oprema, HashMap<Namirnica, Posedovanje> namirnice) {
        if (ime.equals("")) throw new IllegalArgumentException();
        if (prezime.equals("")) throw new IllegalArgumentException();
        if (password.equals("")) throw new IllegalArgumentException();
        if (brojTelefona.equals("")) throw new IllegalArgumentException();
        if (adresa.equals("")) throw new IllegalArgumentException();
        if (korisnik == null) throw new NullPointerException();
        aplikacija.getMenadzerKorisnika().izmeniProfil(korisnik, ime, prezime, password, brojTelefona, adresa, oprema, namirnice);
    }
}

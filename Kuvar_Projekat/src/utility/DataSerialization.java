package utility;


import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class DataSerialization implements Serializable {

    private ArrayList<Korisnik> korisnici;
    private ArrayList<Oprema> oprema;
    private ArrayList<Namirnica> namirnice;
    private ArrayList<Kategorija> kategorije;
    private ArrayList<Recept> recepti;

    public DataSerialization() {
        korisnici = new ArrayList<>();
        oprema = new ArrayList<>();
        namirnice = new ArrayList<>();
        kategorije = new ArrayList<>();
        recepti = new ArrayList<>();
        dataInit();
    }


    public void dataInit() {

        int idFactor = 1000;


        for (int i = 0; i < 4; ++i) {

            this.korisnici.add(new Korisnik("ime" + i, "prezime" + i, new Date(), "username" + i, "password" + i,
                    "brojTelefona" + i, "adresa" + i, Uloga.KUVAR, null, null, null, null, null));

            this.oprema.add(new Oprema(i + idFactor, "oprema" + i));
            idFactor += 1000;

            this.namirnice.add(new Namirnica(i + idFactor, "namirnica" + i));
            idFactor += 1000;

            this.kategorije.add(new Kategorija(i + idFactor, "kategorija" + i, null));
            idFactor += 1000;

            this.recepti.add(new Recept(i + idFactor, "nazivRecepta" + i, Tezina.TESKO, "opis" + i,
                    (float) i + idFactor, null, korisnici.get(i), new ArrayList<>(), null, null));
            idFactor += 1000;
        }


    }

    public void write() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("data.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public DataSerialization read() throws IOException, ClassNotFoundException {

        DataSerialization ds = null;
        try {
            FileInputStream fileIn = new FileInputStream("data.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ds = (DataSerialization) in.readObject();
            in.close();
            fileIn.close();
            return ds;
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;

    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public ArrayList<Oprema> getOprema() {
        return oprema;
    }

    public void setOprema(ArrayList<Oprema> oprema) {
        this.oprema = oprema;
    }

    public ArrayList<Namirnica> getNamirnice() {
        return namirnice;
    }

    public void setNamirnice(ArrayList<Namirnica> namirnice) {
        this.namirnice = namirnice;
    }

    public ArrayList<Kategorija> getKategorije() {
        return kategorije;
    }

    public void setKategorije(ArrayList<Kategorija> kategorije) {
        this.kategorije = kategorije;
    }

    public ArrayList<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(ArrayList<Recept> recepti) {
        this.recepti = recepti;
    }
}

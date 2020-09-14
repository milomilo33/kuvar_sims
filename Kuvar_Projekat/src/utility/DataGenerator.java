package utility;

import model.*;

import java.util.*;

public class DataGenerator {
    public static void generisiPodatke(Aplikacija aplikacija) {
        for (int i = 0; i < 3; i++) {
            Kategorija ki = new Kategorija(IDGenerator.INSTANCE.requestID(), "kategorija " + i, new ArrayList<>());
            for (int j = i; j < 3; j++) {
                Kategorija kj = new Kategorija(IDGenerator.INSTANCE.requestID(), "kategorija " + i + j, new ArrayList<>());
                for (int k = j; k < 3; k++) {
                    Kategorija kk = new Kategorija(IDGenerator.INSTANCE.requestID(), "kategorija" + i + j + k, new ArrayList<>());
                    kk.getPotkategorije().add(kj);
                }
                ki.getPotkategorije().add(kj);
            }
            aplikacija.menadzerKategorija.getKategorije().add(ki);
        }
        for (int i = 0; i < 10; i++) {
            aplikacija.menadzerNamirnica.getNamirnice().add(new Namirnica(IDGenerator.INSTANCE.requestID(), "namirnica" + i));
        }
        for (int i = 0; i < 10; i++) {
            aplikacija.menadzerOpreme.getOprema().add(new Oprema(IDGenerator.INSTANCE.requestID(), "oprema" + i));
        }
        for (int i = 0; i < 5; i++) {
            aplikacija.menadzerKorisnika.getKorisnici().add(new Korisnik("pera" + i, "peric" + i, new Date(), "supermario" + i, "super" + i, "tel" + i * i * i, "novi grad" + i));
        }
        aplikacija.setTrenutniKorisnik(aplikacija.menadzerKorisnika.getKorisnici().get(0));


        int end = new Random().nextInt(10);
        if (end <= 0) end = 1;
        for (int i = 0; i < 10; i++) {
            List<Komentar> randK = new ArrayList<>();
            int pp = new Random().nextInt(5);
            for (int j = 0; j < pp; j++) {
                int oc = new Random().nextInt(10);
                int tempf = new Random().nextInt(4);
                randK.add(new Komentar("komentar" + j, new Date(), aplikacija.menadzerKorisnika.getKorisnici().get(tempf), oc));
            }
            List<Kategorija> randKat = new ArrayList<>();
            for (int j = 0; j < pp; j++) {
                randKat.add(new Kategorija(IDGenerator.INSTANCE.requestID(), "kategorija" + j, null));
            }
            int temp = new Random().nextInt(5);
            HashMap<Namirnica, Sastojanje> namirnicaSastojanje = new HashMap<>();
            for (int j = 0; j < 10; ++j)
                namirnicaSastojanje.put(aplikacija.menadzerNamirnica.getNamirnice().get(j), new Sastojanje((float) i * 5, MernaJedinica.values()[new Random().nextInt(MernaJedinica.values().length)]));
            aplikacija.menadzerRecepata.getRecepti().add(new Recept(IDGenerator.INSTANCE.requestID(), "recept" + i, Tezina.values()[new Random().nextInt(Tezina.values().length)],
                    "opis" + i, (float) i * 10, namirnicaSastojanje, aplikacija.menadzerKorisnika.getKorisnici().get(temp), aplikacija.menadzerOpreme.getOprema(), randK, randKat));
        }
        aplikacija.setTrenutniKorisnik(null);
    }
}

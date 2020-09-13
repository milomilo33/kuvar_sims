package model;

import utility.IDGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Aplikacija {
	private Korisnik trenutniKorisnik;
	private MenadzerKorisnika menadzerKorisnika;
	private MenadzerRecepata menadzerRecepata;
	private MenadzerNamirnica menadzerNamirnica;
	private MenadzerOpreme menadzerOpreme;

	public MenadzerKorisnika getMenadzerKorisnika() {
		return menadzerKorisnika;
	}

	public MenadzerRecepata getMenadzerRecepata() {
		return menadzerRecepata;
	}

	public MenadzerNamirnica getMenadzerNamirnica() {
		return menadzerNamirnica;
	}

	public MenadzerOpreme getMenadzerOpreme() {
		return menadzerOpreme;
	}

	public MenadzerKategorija getMenadzerKategorija() {
		return menadzerKategorija;
	}

	private MenadzerKategorija menadzerKategorija;

	public Aplikacija() throws IOException, ClassNotFoundException {
		this.trenutniKorisnik = null;
		this.menadzerKategorija = new MenadzerKategorija();
		this.menadzerKorisnika = new MenadzerKorisnika();
		this.menadzerNamirnica = new MenadzerNamirnica();
		this.menadzerOpreme = new MenadzerOpreme();
		this.menadzerRecepata = new MenadzerRecepata();
	}

	public void sacuvajStanje() throws IOException {
		this.menadzerRecepata.serialize();
		this.menadzerOpreme.serialize();
		this.menadzerNamirnica.serialize();
		this.menadzerKorisnika.serialize();
		this.menadzerKategorija.serialize();
	}

	public Korisnik getTrenutniKorisnik() {
		return trenutniKorisnik;
	}

	public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
		this.trenutniKorisnik = trenutniKorisnik;
	}

	public class MenadzerKorisnika {
		private ArrayList<Korisnik> korisnici;

		public MenadzerKorisnika() throws IOException, ClassNotFoundException {
			this.korisnici = new ArrayList<>();
			this.deserialize();
		}

		public void serialize() throws IOException {
			try {
				FileOutputStream fileOut = new FileOutputStream("data/korisnici.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(korisnici);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}

		public void deserialize() throws IOException, ClassNotFoundException {
			try {
				FileInputStream fileIn = new FileInputStream("data/korisnici.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				korisnici = (ArrayList<Korisnik>) in.readObject();
				fileIn.close();
				in.close();
			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}

		public boolean dodajNovogKorisnika(String ime, String prezime, Date datumRodjenja, String username, String password,
										   String brojTelefona, String adresa) {
			Korisnik noviKorisnik = new Korisnik(ime, prezime, datumRodjenja, username, password, brojTelefona, adresa);
			for (Korisnik korisnik : korisnici) {
				if (korisnik.proveriKorisnika(noviKorisnik)) return false;
			}
			korisnici.add(noviKorisnik);
			return true;

		}
	}

	public class MenadzerRecepata {
		private ArrayList<Recept> recepti;

		public MenadzerRecepata() throws IOException, ClassNotFoundException {
			this.recepti = new ArrayList<>();
			this.deserialize();
		}

		public void serialize() throws IOException {
			try {
				FileOutputStream fileOut = new FileOutputStream("data/recepti.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(recepti);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}

		public void deserialize() throws IOException, ClassNotFoundException {
			try {
				FileInputStream fileIn = new FileInputStream("data/recepti.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				recepti = (ArrayList<Recept>) in.readObject();
				fileIn.close();
				in.close();
			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}

		public boolean dodajNoviRecept(String naziv, Tezina tezina, String opis,
									   Float vremePripreme, List<Kategorija> kategorije, List<Namirnica> namirnice, List<Float> kolicine, List<MernaJedinica> merneJedinice, List<Oprema> opreme) {

			HashMap<Namirnica, Sastojanje> namirnicaSastojanje = new HashMap<>();
			for (int i = 0; i < namirnice.size(); ++i)
				namirnicaSastojanje.put(namirnice.get(i), new Sastojanje(kolicine.get(i), merneJedinice.get(i)));
			recepti.add(new Recept(IDGenerator.INSTANCE.requestID(), naziv, tezina, opis, vremePripreme, namirnicaSastojanje, trenutniKorisnik, opreme, null, null));
			return true;
		}

		public void pretraziRecepte(List<Recept> rezultatiPretrage, String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme) {
			for (Recept recept : recepti)
				if (recept.proveraKriterijuma(naziv, kategorije, namirnice, tezina, oprema, vremePripreme))
					dodajRezultatPretrage(recept, rezultatiPretrage);
		}

		public void dodajRezultatPretrage(Recept recept, List<Recept> rezultatiPretrage) {
			rezultatiPretrage.add(recept);
		}
	}

	public class MenadzerNamirnica {
		private ArrayList<Namirnica> namirnice;

		public MenadzerNamirnica() throws IOException, ClassNotFoundException {
			this.namirnice = new ArrayList<>();
			this.deserialize();
		}

		public void serialize() throws IOException {
			try {
				FileOutputStream fileOut = new FileOutputStream("data/namirnice.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(namirnice);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}

		public void deserialize() throws IOException, ClassNotFoundException {
			try {
				FileInputStream fileIn = new FileInputStream("data/namirnice.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				namirnice = (ArrayList<Namirnica>) in.readObject();
				fileIn.close();
				in.close();
			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}
		
		public ArrayList<Namirnica> getNamirnice() {
			return this.namirnice;
		}
	}

	public class MenadzerKategorija {
		private ArrayList<Kategorija> kategorije;

		public MenadzerKategorija() throws IOException, ClassNotFoundException {
			this.kategorije = new ArrayList<>();
			this.deserialize();
		}

		public void serialize() throws IOException {
			try {
				FileOutputStream fileOut = new FileOutputStream("data/kategorije.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(kategorije);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}

		public void deserialize() throws IOException, ClassNotFoundException {
			try {
				FileInputStream fileIn = new FileInputStream("data/kategorije.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				kategorije = (ArrayList<Kategorija>) in.readObject();
				fileIn.close();
				in.close();
			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}
	}

	public class MenadzerOpreme {
		private ArrayList<Oprema> oprema;

		public MenadzerOpreme() throws IOException, ClassNotFoundException {
			this.oprema = new ArrayList<>();
			this.deserialize();
		}

		public void serialize() throws IOException {
			try {
				FileOutputStream fileOut = new FileOutputStream("data/opreme.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(oprema);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}

		public void deserialize() throws IOException, ClassNotFoundException {
			try {
				FileInputStream fileIn = new FileInputStream("data/opreme.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				oprema = (ArrayList<Oprema>) in.readObject();
				fileIn.close();
				in.close();
			} catch (IOException | ClassNotFoundException i) {
				i.printStackTrace();
			}
		}
	}


}

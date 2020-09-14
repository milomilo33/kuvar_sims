package model;

import event.Observer;
import event.UpdateEvent;
import utility.IDGenerator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Aplikacija {

	public MenadzerKategorija menadzerKategorija;

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

	private Korisnik trenutniKorisnik;

	public MenadzerKorisnika menadzerKorisnika;
	public MenadzerRecepata menadzerRecepata;
	public MenadzerNamirnica menadzerNamirnica;
	public MenadzerOpreme menadzerOpreme;


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

	public class MenadzerKorisnika implements Publisher{
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
		
		public List<Korisnik> getKorisnici(){
			return korisnici;
		}

		private Boolean uspesnoRegistrovan;
		
		public Boolean getUspesnoRegistrovan() {
			return uspesnoRegistrovan;
		}
		
		public void dodajNovogKorisnika(String ime, String prezime, LocalDate datumRodjenja, String username, String password,
										   String brojTelefona, String adresa) {
			Korisnik noviKorisnik = new Korisnik(ime, prezime, datumRodjenja, username, password, brojTelefona, adresa);
			uspesnoRegistrovan = true;
			for (Korisnik korisnik : korisnici) {
				if (korisnik.proveriKorisnika(noviKorisnik)) {
					uspesnoRegistrovan = false;
					break;
				}
			}
			if (uspesnoRegistrovan)
				korisnici.add(noviKorisnik);
			notifyObservers();
		}
		
		/*private Korisnik uspesnoPrijavljenKorisnik;
		
		public Korisnik getUspesnoPrijavljenKorisnik() {
			return uspesnoPrijavljenKorisnik;
		}*/
		
		public void prijaviKorisnika(String username, String password) throws IllegalArgumentException {
			for (Korisnik korisnik : korisnici)
				if (korisnik.getUsername().equals(username)) {
					if (korisnik.getPassword().equals(password)) {
						trenutniKorisnik = korisnik;
						notifyObservers();
						return;
					}
					else
						throw new IllegalArgumentException("Uneli ste pogresnu sifru!");
				}
			throw new IllegalArgumentException("Uneseno korisnicko ime ne postoji!");
		}
		
		public void pretplatiSe(Korisnik k, AtomicBoolean retVal) {
			retVal.set(trenutniKorisnik.pretplatiSe(k));
			notifyObservers();
		}
		
		private List<Observer> observers;
		
		@Override
		public void addObserver(Observer observer) {
			if (null == observers)
				observers = new ArrayList<Observer>();
			else
				observers.clear();
			observers.add(observer);
		}

		@Override
		public void removeObserver(Observer observer) {
			if (null == observers)
				return;
			observers.remove(observer);
		}

		@Override
		public void notifyObservers() {
			UpdateEvent e = new UpdateEvent(this);
			for (Observer observer : observers) {
				observer.updatePerformed(e);
			}
		}
	}

	public class MenadzerRecepata implements Publisher {
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
		
		public void dodajKomentar(String tekst, Date datum, Korisnik autor ,Integer ocena, Recept r, StringBuilder srOcena) {
			r.dodajKomentar(tekst, datum, autor, ocena);
			srOcena.delete(0, srOcena.length());
			srOcena.append(r.getSrednjaOcena().toString());
			notifyObservers();
		}

		private List<Observer> observers;

		public List<Recept> getRecepti() {
			return recepti;
		}

		public void pretraziRecepte(List<Recept> rezultatiPretrage, String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme) {
			for (Recept recept : recepti)
				if (recept.proveraKriterijuma(naziv, kategorije, namirnice, tezina, oprema, vremePripreme))
					dodajRezultatPretrage(recept, rezultatiPretrage);
			notifyObservers();
		}

		public void dodajRezultatPretrage(Recept recept, List<Recept> rezultatiPretrage) {
			rezultatiPretrage.add(recept);
		}
			
		@Override
		public void addObserver(Observer observer) {
			if (null == observers)
				observers = new ArrayList<Observer>();
			observers.add(observer);
		}

		@Override
		public void removeObserver(Observer observer) {
			if (null == observers)
				return;
			observers.remove(observer);
		}

		@Override
		public void notifyObservers() {
			UpdateEvent e = new UpdateEvent(this);
			for (Observer observer : observers) {
				observer.updatePerformed(e);
			}
		}

		public boolean dodajNoviRecept(String naziv, Tezina tezina, String opis,
									   Float vremePripreme, List<Kategorija> kategorije, List<Namirnica> namirnice, List<Float> kolicine, List<MernaJedinica> merneJedinice, List<Oprema> opreme) {

			HashMap<Namirnica, Sastojanje> namirnicaSastojanje = new HashMap<>();
			for (int i = 0; i < namirnice.size(); ++i)
				namirnicaSastojanje.put(namirnice.get(i), new Sastojanje(kolicine.get(i), merneJedinice.get(i)));
			recepti.add(new Recept(IDGenerator.INSTANCE.requestID(), naziv, tezina, opis, vremePripreme, namirnicaSastojanje, trenutniKorisnik, opreme, null, kategorije));
			notifyObservers();
			return true;
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

	public class MenadzerKategorija implements Publisher {
		private ArrayList<Kategorija> kategorije;

		private Kategorija parentKategorija;

		private List<Observer> observers;

		public MenadzerKategorija() throws IOException, ClassNotFoundException {
			this.parentKategorija = null;
			this.kategorije = new ArrayList<>();
			this.deserialize();
		}

		public void dodajKategoriju(Kategorija parent, String naziv) {
			if (parent != null) {
				for (Kategorija k : parent.getPotkategorije()) {
					if (k.getNaziv().equals(naziv)) throw new IllegalArgumentException();
				}
				parent.getPotkategorije().add(new Kategorija(IDGenerator.INSTANCE.requestID(), naziv, new ArrayList<Kategorija>()));
			} else kategorije.add(new Kategorija(IDGenerator.INSTANCE.requestID(), naziv, new ArrayList<Kategorija>()));
			notifyObservers();
		}

		public ArrayList<Kategorija> getKategorije() {
			return kategorije;
		}

		private void findKategorija(Kategorija kriterijumKategorija, Kategorija currentKategorija) {
			if (!currentKategorija.getPotkategorije().isEmpty()) {
				for (Kategorija k : currentKategorija.getPotkategorije()) {
					if (kriterijumKategorija.getSifraKategorije().equals(k.getSifraKategorije())) {
						parentKategorija = currentKategorija;
					}
					if (parentKategorija != null) return;
					findKategorija(kriterijumKategorija, k);
				}
				if (parentKategorija != null) return;
			}
		}

		public void obrisiKategoriju(Kategorija kategorija) {
			parentKategorija = null;
			for (Kategorija k : kategorije) {
				if (k.equals(kategorija)) {
					kategorije.remove(k);
					notifyObservers();
					return;
				}
			}

			for (Kategorija k : kategorije) findKategorija(kategorija, k);
			if (parentKategorija == null) throw new NullPointerException();
			parentKategorija.getPotkategorije().remove(kategorija);
			notifyObservers();
		}

		public void preimenujKategoriju(Kategorija kategorija, String naziv) {
			parentKategorija = null;
			for (Kategorija k : kategorije) {
				if (k.equals(kategorija)) {
					parentKategorija = k;
					break;
				}
			}
			if (parentKategorija != null) {
				for (Kategorija k : kategorije) {
					if (k.getNaziv().equals(naziv)) throw new IllegalArgumentException();
				}
				kategorija.setNaziv(naziv);
			}
			for (Kategorija k : kategorije) findKategorija(kategorija, k);
			if (parentKategorija != null) {
				for (Kategorija k : parentKategorija.getPotkategorije()) {
					if (k.getNaziv().equals(naziv)) throw new IllegalArgumentException();
				}
				kategorija.setNaziv(naziv);
			}
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


		@Override
		public void addObserver(Observer observer) {
			if (null == observers)
				observers = new ArrayList<Observer>();
			observers.add(observer);
		}

		@Override
		public void removeObserver(Observer observer) {
			if (null == observers)
				return;
			observers.remove(observer);
		}

		@Override
		public void notifyObservers() {
			UpdateEvent e = new UpdateEvent(this);
			for (Observer observer : observers) {
				observer.updatePerformed(e);
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
		
		public ArrayList<Oprema> getOprema(){
			return oprema;
		}
	}


}

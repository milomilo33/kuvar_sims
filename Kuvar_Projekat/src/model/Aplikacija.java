package model;

import event.Observer;
import event.UpdateEvent;
import utility.IDGenerator;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
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
					} else
						throw new IllegalArgumentException("Uneli ste pogresnu sifru!");
				}
			throw new IllegalArgumentException("Uneseno korisnicko ime ne postoji!");
		}

		public void pretplatiSe(Korisnik k, AtomicBoolean retVal) {
			retVal.set(trenutniKorisnik.pretplatiSe(k));
			notifyObservers();
		}
		
		public void dodajKnjiguRecepata(String naziv, Map<String, List<Recept>> sekcije) {
			trenutniKorisnik.dodajKnjiguRecepata(naziv, sekcije);
			notifyObservers();
		}
		
		public void obrisiKnjiguRecepata(KnjigaRecepata knjigaRecepata) {
			trenutniKorisnik.obrisiKnjiguRecepata(knjigaRecepata);
			notifyObservers();
		}
		
		public void preimenujKnjiguRecepata(String naziv, KnjigaRecepata knjigaRecepata) {
			trenutniKorisnik.preimenujKnjiguRecepata(naziv, knjigaRecepata);
			notifyObservers();
		}

		public void izmeniProfil(Korisnik korisnik, String ime, String prezime, String password, String brojTelefona, String adresa, ArrayList<Oprema> oprema, HashMap<Namirnica, Posedovanje> namirnice) {
			korisnik.setIme(ime);
			korisnik.setPrezime(prezime);
			korisnik.setPassword(password);
			korisnik.setBrojTelefona(brojTelefona);
			korisnik.setAdresa(adresa);
			korisnik.setOprema(oprema);
			korisnik.setNamirnice(namirnice);
		}
		
		public void kreirajDirektorijumDodajRecept(Folder trenutniDir, String nazivNoviDir, Recept recept) {
			if (trenutniDir.getElementi() == null)
				trenutniDir.setElementi(new ArrayList<ElementBookmarkovanja>());
			if (trenutniDir.potfolderPostoji(nazivNoviDir))
				throw new IllegalArgumentException("Direktorijum vec postoji!");
			Folder noviDirektorijum = new Folder(nazivNoviDir);
			noviDirektorijum.setElementi(new ArrayList<ElementBookmarkovanja>());
			Bookmark bookmark = new Bookmark(recept);
			noviDirektorijum.dodajElement(bookmark);
			trenutniDir.dodajElement(noviDirektorijum);
			notifyObservers();
		}
		
		public void dodajReceptUDirektorijum(Folder trenutniDir, Recept recept) {
			if (trenutniDir.getElementi() == null)
				trenutniDir.setElementi(new ArrayList<ElementBookmarkovanja>());
			Bookmark bookmark = new Bookmark(recept);
			trenutniDir.dodajElement(bookmark);
			notifyObservers();
		}
		
		public void preimenujElementBookmarkovanja(ElementBookmarkovanja izabraniElement, String noviNaziv, Folder sadrzeciFolder) {
			for (ElementBookmarkovanja eb : sadrzeciFolder.getElementi())
				if (eb.getClass().equals(izabraniElement.getClass()))
					if (eb.getNaziv().equals(noviNaziv)) {
						if (eb instanceof Folder)
							throw new IllegalArgumentException("Direktorijum sa tim nazivom vec postoji!");
						else if (eb instanceof Bookmark)
							throw new IllegalArgumentException("Bookmark sa tim nazivom vec postoji!");
					}
			izabraniElement.setNaziv(noviNaziv);
			notifyObservers();
		}
		
		public void obrisiElementBookmarkovanja(ElementBookmarkovanja izabraniElement, Folder sadrzeciFolder, Folder folderSadrzecegFoldera) {
			sadrzeciFolder.getElementi().remove(izabraniElement);
			if (sadrzeciFolder.getElementi().isEmpty() && folderSadrzecegFoldera != null)
				folderSadrzecegFoldera.getElementi().remove(sadrzeciFolder);
			notifyObservers();
		}
		
		public Folder getRootDir() {
			if (trenutniKorisnik.getElementiBookmarkovanja() == null)
				trenutniKorisnik.setElementiBookmarkovanja(new ArrayList<ElementBookmarkovanja>());
			if (trenutniKorisnik.getElementiBookmarkovanja().size() == 0)
				trenutniKorisnik.getElementiBookmarkovanja().add(new Folder("Opsti direktorijum"));
			return (Folder) trenutniKorisnik.getElementiBookmarkovanja().get(0);
		}

		private List<Observer> observers;

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

		public void dodajKomentar(String tekst, Date datum, Korisnik autor, Integer ocena, Recept r, StringBuilder srOcena) {
			r.dodajKomentar(tekst, datum, autor, ocena);
			srOcena.delete(0, srOcena.length());
			srOcena.append(r.getSrednjaOcena().toString());
			notifyObservers();
		}

		public void sortiraj() {
			this.recepti.sort(Comparator.comparing(Recept::getSrednjaOcena).reversed());
		}

		private List<Observer> observers;

		public List<Recept> getRecepti() {
			return recepti;
		}

		public void pretraziRecepte(List<Recept> rezultatiPretrage, String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme, Map<Namirnica, Posedovanje> namirniceSaPosedovanjem) {
			for (Recept recept : recepti)
				if (recept.proveraKriterijuma(naziv, kategorije, namirnice, tezina, oprema, vremePripreme, namirniceSaPosedovanjem))
					dodajRezultatPretrage(recept, rezultatiPretrage);
			notifyObservers();
		}

		public void dodajRezultatPretrage(Recept recept, List<Recept> rezultatiPretrage) {
			rezultatiPretrage.add(recept);
		}
		
		public boolean verifikacijaRecepta(Recept recept) {
			if(trenutniKorisnik == null)
				return true;
			return recept.getAutor().equals(trenutniKorisnik);
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
			recepti.add(new Recept(IDGenerator.INSTANCE.requestID(), naziv, tezina, opis, vremePripreme, namirnicaSastojanje, trenutniKorisnik, opreme, new ArrayList<Komentar>(), kategorije));
			trenutniKorisnik.dodajReceptKorisnika(recepti.get(recepti.size() - 1));
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
		
		public void dodajNamirnicu(String naziv) {
			Integer sifraNamirnice = IDGenerator.INSTANCE.requestID();
			this.namirnice.add(new Namirnica(sifraNamirnice, naziv));
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

		public void dodajKategoriju(Kategorija roditelj, String naziv) {
			if (roditelj != null) {
				for (Kategorija k : roditelj.getPotkategorije()) {
					if (k.getNaziv().equals(naziv)) throw new IllegalArgumentException();
				}
				roditelj.getPotkategorije().add(new Kategorija(IDGenerator.INSTANCE.requestID(), naziv, new ArrayList<>()));
			} else {
				for (Kategorija k : this.kategorije)
					if (k.getNaziv().equals(naziv)) throw new IllegalArgumentException();
				kategorije.add(new Kategorija(IDGenerator.INSTANCE.requestID(), naziv, new ArrayList<>()));
			}
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
		
		public void dodajOpremu(String naziv) {
			Integer sifraOpreme = IDGenerator.INSTANCE.requestID();
			this.oprema.add(new Oprema(sifraOpreme, naziv));
		}
	}


}

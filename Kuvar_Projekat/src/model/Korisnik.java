package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Korisnik implements Serializable {
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String username;
	private String password;
	private String brojTelefona;
	private String adresa;
	private Uloga uloga;
	private List<Korisnik> jePretplacen;
	private List<Recept> recepti;
	private Map<Namirnica, Posedovanje> namirnice;
	private List<Oprema> oprema;
	private List<KnjigaRecepata> knjigeRecepata;
	private List<ElementBookmarkovanja> elementiBookmarkovanja;
	
	public Korisnik() {}
	public Korisnik(String ime, String prezime, LocalDate datumRodjenja, String username, String password,
			String brojTelefona, String adresa, Uloga uloga, List<Korisnik> jePretplacen, List<Recept> recepti, Map<Namirnica, Posedovanje> namirnice, List<Oprema> oprema, List<KnjigaRecepata> knjigeRecepata) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.username = username;
		this.password = password;
		this.brojTelefona = brojTelefona;
		this.adresa = adresa;
		this.uloga = uloga;
		this.jePretplacen = jePretplacen;
		this.recepti = recepti;
		this.namirnice = namirnice;
		this.oprema = oprema;
		this.knjigeRecepata = knjigeRecepata;
	}

	public Korisnik(String ime, String prezime, LocalDate datumRodjenja, String username, String password, String brojTelefona, String adresa) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.username = username;
		this.password = password;
		this.brojTelefona = brojTelefona;
		this.adresa = adresa;
		this.uloga = Uloga.KUVAR;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	public List<Korisnik> getJePretplacen() {
		return jePretplacen;
	}
	public void setJePretplacen(List<Korisnik> jePretplacen) {
		this.jePretplacen = jePretplacen;
	}
	public List<Recept> getRecepti() {
		return recepti;
	}
	public void setRecepti(List<Recept> recepti) {
		this.recepti = recepti;
	}
	
	public Map<Namirnica, Posedovanje> getNamirnice() {
		return namirnice;
	}
	public void setNamirnice(Map<Namirnica, Posedovanje> namirnice) {
		this.namirnice = namirnice;
	}
	public List<Oprema> getOprema() {
		return oprema;
	}
	public void setOprema(List<Oprema> oprema) {
		this.oprema = oprema;
	}
	public List<KnjigaRecepata> getKnjigeRecepata() {
		return knjigeRecepata;
	}
	public void setKnjigeRecepata(List<KnjigaRecepata> knjigeRecepata) {
		this.knjigeRecepata = knjigeRecepata;
	}
	
	public List<ElementBookmarkovanja> getElementiBookmarkovanja() {
		return elementiBookmarkovanja;
	}
	public void setElementiBookmarkovanja(List<ElementBookmarkovanja> elementiBookmarkovanja) {
		this.elementiBookmarkovanja = elementiBookmarkovanja;
	}
	public boolean pretplatiSe(Korisnik korisnik) {
		if(this.jePretplacen != null)
		if(this.jePretplacen.contains(korisnik))
			return false;
		else {
			this.jePretplacen.add(korisnik);
			return true;
		}
		else {
			this.jePretplacen = new ArrayList<>();
			this.jePretplacen.add(korisnik);
			return true;
		}
	}
	
	public void dodajReceptKorisnika(Recept r) {
		this.recepti.add(r);
	}
	
	public Boolean proveriKorisnika(Korisnik korisnik) {
		return this.username.equals(korisnik.getUsername());
	}
	
	public void dodajKnjiguRecepata(String naziv, Map<String, List<Recept>> sekcije) {
		if(this.knjigeRecepata == null)
			this.knjigeRecepata = new ArrayList<>();
		else {
			boolean nadjen = false;
			for(KnjigaRecepata kr:this.knjigeRecepata)
				if(naziv.equals(kr.getNaziv()))
					nadjen = true;
			if(nadjen)
				throw new NumberFormatException();
		}
		this.knjigeRecepata.add(new KnjigaRecepata(naziv, sekcije));
	}
	
	@Override
	public String toString() {
		return username;
	}
	@Override
	public boolean equals(Object o) {
		Korisnik c = (Korisnik)o;
		return this.username.equals(c.getUsername());
	}
}

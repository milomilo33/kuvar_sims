package model;

import java.util.Date;
import java.util.List;

public class Aplikacija {
	private List<Korisnik> korisnici;
	private List<Recept> recepti;
	public Aplikacija() {}
	public Aplikacija(List<Korisnik> korisnici, List<Recept> recepti) {
		this.korisnici = korisnici;
		this.recepti = recepti;
	}
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}
	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	public List<Recept> getRecepti() {
		return recepti;
	}
	public void setRecepti(List<Recept> recepti) {
		this.recepti = recepti;
	}
	public void dodajNovogKorisnika(String ime, String prezime, Date datumRodjenja, String username, String password,
			String brojTelefona, String adresa) {
		
	}
	public void dodajNoviRecept(String naziv, Tezina tezina, String opis,
			Float vremePripreme, List<Kategorija> kategorije, List<Namirnica> namirnice, List<Float> kolicine, List<MernaJedinica> merneJedinice, List<Oprema> opreme) {
		
	}
	public void pretraziRecepte(List<Recept> rezultatiPretrage, String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme) {
		
	}
	public void dodajRezultatPretrage(Recept recept, List<Recept> rezultatiPretrage) {
		
	}
}

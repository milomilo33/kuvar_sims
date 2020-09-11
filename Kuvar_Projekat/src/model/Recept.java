package model;

import java.util.List;
import java.util.Map;

public class Recept {
	private Integer sifraRecepata;
	private String naziv;
	private Tezina tezina;
	private String opis;
	private Float vremePripreme;
	private Map<Namirnica,Sastojanje> namirniceSaSastojanjem;
	private Korisnik autor;
	private List<Oprema> oprema;
	private List<Komentar> komentari;
	public Recept() {}

	public Recept(Integer sifraRecepata, String naziv, Tezina tezina, String opis,
			Float vremePripreme, Map<Namirnica,Sastojanje> namirniceSaSastojanjem, Korisnik autor, List<Oprema> oprema, List<Komentar> komentari) {
		this.sifraRecepata = sifraRecepata;
		this.naziv = naziv;
		this.tezina = tezina;
		this.opis = opis;
		this.vremePripreme = vremePripreme;
		this.namirniceSaSastojanjem = namirniceSaSastojanjem;
		this.autor = autor;
		this.oprema = oprema;
		this.komentari = komentari;
	}

	public Integer getSifraRecepata() {
		return sifraRecepata;
	}

	public void setSifraRecepata(Integer sifraRecepata) {
		this.sifraRecepata = sifraRecepata;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Tezina getTezina() {
		return tezina;
	}

	public void setTezina(Tezina tezina) {
		this.tezina = tezina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Float getVremePripreme() {
		return vremePripreme;
	}

	public void setVremePripreme(Float vremePripreme) {
		this.vremePripreme = vremePripreme;
	}

	public Map<Namirnica,Sastojanje> getNamirniceSaSastojanjem() {
		return namirniceSaSastojanjem;
	}

	public void setNamirniceSaSastojanjem(Map<Namirnica,Sastojanje> namirniceSaSastojanjem) {
		this.namirniceSaSastojanjem = namirniceSaSastojanjem;
	}
	public Float getSrednjaOcena() {
		Integer suma = 0;
		for (Komentar k : this.komentari)
			suma += k.getOcena();
		return (float) (suma / this.komentari.size());
	}
	public Korisnik getAutor() {
		return autor;
	}

	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	public List<Oprema> getOprema() {
		return oprema;
	}

	public void setOprema(List<Oprema> oprema) {
		this.oprema = oprema;
	}

	public List<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public void dodajKomentar(Komentar komentar) {
		this.komentari.add(komentar);
	}
	public void dodajNamirnicuSaSastojanjem(Namirnica n, Sastojanje s) {
		this.namirniceSaSastojanjem.put(n, s);
	}
	public Boolean proveraKriterijuma(String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme) {
		return true;
	}
}

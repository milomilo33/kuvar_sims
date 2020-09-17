package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Recept implements Serializable {
	private Integer sifraRecepata;
	private String naziv;
	private Tezina tezina;
	private String opis;
	private Float vremePripreme;
	private Map<Namirnica, Sastojanje> namirniceSaSastojanjem;
	private Korisnik autor;
	private List<Oprema> oprema;
	private List<Komentar> komentari;
	private List<Kategorija> kategorije;

	public Recept() {
	}

	public Recept(Integer sifraRecepata, String naziv, Tezina tezina, String opis,
				  Float vremePripreme, Map<Namirnica, Sastojanje> namirniceSaSastojanjem, Korisnik autor, List<Oprema> oprema, List<Komentar> komentari, List<Kategorija> kategorije) {
		this.sifraRecepata = sifraRecepata;
		this.naziv = naziv;
		this.tezina = tezina;
		this.opis = opis;
		this.vremePripreme = vremePripreme;
		this.namirniceSaSastojanjem = namirniceSaSastojanjem;
		this.autor = autor;
		this.oprema = oprema;
		this.komentari = komentari;
		this.kategorije = kategorije;
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
		if (this.komentari.size() > 0) return (float) suma / (float) this.komentari.size();
		else return (float) suma;
	}

	public Korisnik getAutor() {
		return autor;
	}

	public List<Kategorija> getKategorije() {
		return kategorije;
	}

	public void setKategorije(List<Kategorija> kategorije) {
		this.kategorije = kategorije;
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

	public void dodajKomentar(String tekst, Date datum, Korisnik autor ,Integer ocena) {
		Komentar komentar = new Komentar(tekst, datum, autor, ocena);
		if(this.komentari==null)
			this.komentari = new ArrayList<>();
		komentari.add(komentar);
	}
	public void dodajNamirnicuSaSastojanjem(Namirnica n, Sastojanje s) {
		this.namirniceSaSastojanjem.put(n, s);
	}
	public Boolean proveraKriterijuma(String naziv, List<Kategorija> kategorije, List<Namirnica> namirnice, Tezina tezina, List<Oprema> oprema, Float vremePripreme, Map<Namirnica, Posedovanje> namirniceSaPosedovanjem) {
		Boolean receptProveren = true;
		if(!this.naziv.contains(naziv))
			receptProveren = false;
		if(kategorije != null)
		for(Kategorija k:kategorije)
		    if(!this.kategorije.contains(k)) {
		    	receptProveren = false;
		    	break;
		    }
		if(namirnice != null) {
			List<Namirnica> tmp = new ArrayList<>();
			for(Map.Entry<Namirnica, Sastojanje> entry: this.namirniceSaSastojanjem.entrySet())
				tmp.add(entry.getKey());
			for(Namirnica n: namirnice) {
				if(!tmp.contains(n)) {
					receptProveren = false;
					break;
				}
			}
		}
		if(oprema != null)
		for(Oprema o:oprema)
			if(!this.oprema.contains(o)) {
				receptProveren = false;
				break;
			}
		if(!this.tezina.equals(tezina))
			receptProveren = false;
		if(this.vremePripreme > vremePripreme)
			receptProveren = false;
		if(namirniceSaPosedovanjem != null) {
			List<Namirnica> tmp = new ArrayList<>();
			for(Map.Entry<Namirnica, Sastojanje> entry: this.namirniceSaSastojanjem.entrySet())
				tmp.add(entry.getKey());
			for(Map.Entry<Namirnica, Posedovanje> entry1: namirniceSaPosedovanjem.entrySet())
				if(tmp.contains(entry1.getKey())) {
					float val1 = 0, val2 = 0;
					if(entry1.getValue().getMernaJedinica().equals(MernaJedinica.KG))
						val1 += entry1.getValue().getKolicina()*1000000;
					if(entry1.getValue().getMernaJedinica().equals(MernaJedinica.G))
						val1 += entry1.getValue().getKolicina()*1000;
					if(entry1.getValue().getMernaJedinica().equals(MernaJedinica.MG))
						val1 += entry1.getValue().getKolicina();
					if(this.namirniceSaSastojanjem.get(entry1.getKey()).getMernaJedinica().equals(MernaJedinica.KG))
						val2 += this.namirniceSaSastojanjem.get(entry1.getKey()).getKolicina()*1000000;
					if(this.namirniceSaSastojanjem.get(entry1.getKey()).getMernaJedinica().equals(MernaJedinica.G))
						val2 += this.namirniceSaSastojanjem.get(entry1.getKey()).getKolicina()*1000;
					if(this.namirniceSaSastojanjem.get(entry1.getKey()).getMernaJedinica().equals(MernaJedinica.MG))
						val2 += this.namirniceSaSastojanjem.get(entry1.getKey()).getKolicina();
					if(val1 > val2)
						receptProveren = false;
				}else receptProveren = false;		
		}
		return receptProveren;
	}
	@Override
	public String toString() {
		return naziv;
	}
}

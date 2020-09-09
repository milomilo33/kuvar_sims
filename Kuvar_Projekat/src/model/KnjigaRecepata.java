package model;

import java.util.List;
import java.util.Map;

public class KnjigaRecepata {
	private String naziv;
	private Map<String, List<Recept>>sekcijeRecepti;
	public KnjigaRecepata() {}
	public KnjigaRecepata(String naziv, Map<String, List<Recept>> sekcijeRecepti) {
		this.naziv = naziv;
		this.sekcijeRecepti = sekcijeRecepti;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Map<String, List<Recept>> getSekcijeRecepti() {
		return sekcijeRecepti;
	}
	public void setSekcijeRecepti(Map<String, List<Recept>> sekcijeRecepti) {
		this.sekcijeRecepti = sekcijeRecepti;
	}
	
}

package controller;

import model.Aplikacija;
import model.ElementBookmarkovanja;
import model.Folder;
import model.Recept;
import utility.KorisneMetode;

public class KontrolerBookmarkovanja {
	private Aplikacija aplikacija;
	
	public KontrolerBookmarkovanja(Aplikacija app) {
		this.aplikacija = app;
	}
	
	public void kreirajDirektorijumDodajRecept(Folder trenutniDir, String nazivNoviDir, Recept recept) {
		if (!KorisneMetode.isNotAnEmptyOrNullString(nazivNoviDir))
			throw new IllegalArgumentException("Nevalidno ime direktorijuma!");
		aplikacija.getMenadzerKorisnika().kreirajDirektorijumDodajRecept(trenutniDir, nazivNoviDir, recept);
	}
	
	public void dodajReceptUDirektorijum(Folder trenutniDir, Recept recept) {
		aplikacija.getMenadzerKorisnika().dodajReceptUDirektorijum(trenutniDir, recept);
	}
	
	public void preimenujElementBookmarkovanja(ElementBookmarkovanja izabraniElement, String noviNaziv, Folder sadrzeciFolder) {
		if (!KorisneMetode.isNotAnEmptyOrNullString(noviNaziv))
			throw new IllegalArgumentException("Nevalidan naziv!");
		aplikacija.getMenadzerKorisnika().preimenujElementBookmarkovanja(izabraniElement, noviNaziv, sadrzeciFolder);
	}
	
	public void obrisiElementBookmarkovanja(ElementBookmarkovanja izabraniElement, Folder sadrzeciFolder, Folder folderSadrzecegFoldera) {
		aplikacija.getMenadzerKorisnika().obrisiElementBookmarkovanja(izabraniElement, sadrzeciFolder, folderSadrzecegFoldera);
	}
	
}
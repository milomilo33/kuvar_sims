package model;

import java.util.List;

public class Folder extends ElementBookmarkovanja {
	private String naziv;
	private List<ElementBookmarkovanja> elementi;
	public Folder() {
		// TODO Auto-generated constructor stub
	}

	public Folder(String naziv, List<ElementBookmarkovanja> elementi) {
		this.naziv = naziv;
		this.elementi = elementi;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<ElementBookmarkovanja> getElementi() {
		return elementi;
	}

	public void setElementi(List<ElementBookmarkovanja> elementi) {
		this.elementi = elementi;
	}

	@Override
	public void otvoriSe() {
		// TODO Auto-generated method stub

	}
	
	public void dodajElement(ElementBookmarkovanja element) {
		
	}
	
	public void ukloniElement(ElementBookmarkovanja element) {
		
	}
	
}

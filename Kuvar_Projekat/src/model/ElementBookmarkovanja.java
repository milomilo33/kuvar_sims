package model;

import java.io.Serializable;

public abstract class ElementBookmarkovanja implements Serializable {
	protected String naziv;
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}

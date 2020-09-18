package model;

import java.io.Serializable;

public class Bookmark extends ElementBookmarkovanja implements Serializable {

	private Recept recept;
	
	public Bookmark(Recept recept) {
		// TODO Auto-generated constructor stub
		this.recept = recept;
		this.naziv = recept.getNaziv();
	}
	
	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.naziv;
	}
	
}

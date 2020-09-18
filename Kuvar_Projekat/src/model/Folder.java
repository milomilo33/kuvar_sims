package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Folder extends ElementBookmarkovanja implements Serializable {
	private List<ElementBookmarkovanja> elementi;
	public Folder(String naziv) {
		// TODO Auto-generated constructor stub
		this.naziv = naziv;
	}

	public Folder(String naziv, List<ElementBookmarkovanja> elementi) {
		this.naziv = naziv;
		this.elementi = elementi;
	}

	public List<ElementBookmarkovanja> getElementi() {
		return elementi;
	}

	public void setElementi(List<ElementBookmarkovanja> elementi) {
		this.elementi = elementi;
	}
	
	public void dodajElement(ElementBookmarkovanja element) {
		if (this.elementi == null)
			this.elementi = new ArrayList<ElementBookmarkovanja>();
		this.elementi.add(element);
	}
	
	public void ukloniElement(ElementBookmarkovanja element) {
		this.elementi.remove(element);
	}
	
	public Boolean potfolderPostoji(String nazivPotfoldera) {
		for (ElementBookmarkovanja eb : elementi)
			if (eb instanceof Folder)
				if (((Folder) eb).getNaziv().equals(nazivPotfoldera))
					return true;
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.naziv;
	}
}

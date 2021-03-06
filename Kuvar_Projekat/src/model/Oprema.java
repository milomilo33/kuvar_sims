package model;

import java.io.Serializable;

public class Oprema implements Serializable {
	private Integer sifraOpreme;
	private String naziv;

	public Oprema() {
	}

	public Oprema(Integer sifraOpreme, String naziv) {
		this.sifraOpreme = sifraOpreme;
		this.naziv = naziv;
	}

	public Integer getSifraOpreme() {
		return sifraOpreme;
	}

	public void setSifraOpreme(Integer sifraOpreme) {
		this.sifraOpreme = sifraOpreme;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
            return true; 
		if (!(o instanceof Oprema))
            return false; 
        Oprema c = (Oprema) o; 
        return naziv.equals(c.getNaziv()) && sifraOpreme.equals(c.getSifraOpreme()); 
	}

}

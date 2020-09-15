package model;

import java.io.Serializable;

public class Posedovanje implements Serializable {
	private Float kolicina;
	private MernaJedinica mernaJedinica;

	public Posedovanje() {
	}

	public Posedovanje(Float kolicina, MernaJedinica mernaJedinica) {
		this.kolicina = kolicina;
		this.mernaJedinica = mernaJedinica;
	}

	public Float getKolicina() {
		return kolicina;
	}

	public void setKolicina(Float kolicina) {
		this.kolicina = kolicina;
	}

	public MernaJedinica getMernaJedinica() {
		return mernaJedinica;
	}

	public void setMernaJedinica(MernaJedinica mernaJedinica) {
		this.mernaJedinica = mernaJedinica;
	}

	@Override
	public String toString() {
		return kolicina.toString() + " " + mernaJedinica.toString();
	}
}

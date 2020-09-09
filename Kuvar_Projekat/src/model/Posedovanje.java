package model;

public class Posedovanje {
	private Float kolicina;
	private MernaJedinica mernaJedinica;
	public Posedovanje() {}
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
	
}

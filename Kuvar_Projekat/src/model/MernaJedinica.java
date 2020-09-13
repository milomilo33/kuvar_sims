package model;

public enum MernaJedinica {
	MG,
	G,
	KG;

	@Override
	public String toString() {
		if (this.equals(MernaJedinica.MG)) return "mg";
		else if (this.equals(MernaJedinica.G)) return "g";
		else return "kg";
	}
}

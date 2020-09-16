package model;

import java.io.Serializable;

public enum MernaJedinica implements Serializable {
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

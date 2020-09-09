package model;

public class Namirnica {
	private Integer sifraNamirnice;
	private String naziv;
	public Namirnica() {
		
	}
	public Namirnica(Integer sifraNamirnice, String naziv) {
		this.sifraNamirnice = sifraNamirnice;
		this.naziv = naziv;
	}
	public Integer getSifraNamirnice() {
		return sifraNamirnice;
	}
	public void setSifraNamirnice(Integer sifraNamirnice) {
		this.sifraNamirnice = sifraNamirnice;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
}

package Unternehmung;

import Unternehmung.Abteilungen.*;

import java.util.HashMap;
import java.util.Map;

public class Unternehmen {
	// Unternehmenseigenschaften
	private String passwort;
	private String name;
	private float fremdkapital;
	private float eigenkapital;
	// Abteilungen:

	private Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

	public Unternehmen( String name,String passwort, float fremdkapital, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.fremdkapital = fremdkapital;
		this.eigenkapital = eigenkapital;
		//Kennzahl Bekanntheitsgrad = new Kennzahl();
	}

	/**
	 * Methode zum Initialisieren der Abteilungen.
	 */
	public void initDepartments()
	{
		abteilungen.put("marketing", new Marketing());
		abteilungen.put("finanzen", new Finanzen());
		abteilungen.put("FuE",new FuE());
		abteilungen.put("produktion", new Produktion());
		abteilungen.put("sozialeLeistungen", new SozialeLeistungen());
		abteilungen.put("vertrieb", new Vertrieb());

	}

	
	public Unternehmen(String name, String passwort, float fremdkapital) {
		this(name,passwort,fremdkapital,10000);
	}

	// Getter und Setter:
	// Unternehmenseigenschaften:
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getFremdkapital() {
		return fremdkapital;
	}
	public void setFremdkapital(float fremdkapital) {
		this.fremdkapital = fremdkapital;
	}
	public float getEigenkapital() {
		return eigenkapital;
	}
	public void setEigenkapital(float eigenkapital) {
		this.eigenkapital = eigenkapital;
	}

	// Abteilungen:
	public Abteilung getAbteilung(String key)
	{
		return abteilungen.get(key);
	}
}

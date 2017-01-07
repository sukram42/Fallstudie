package Unternehmung;

import Unternehmung.Abteilungen.*;

import java.util.HashMap;
import java.util.Map;

public class Unternehmen {

	private String passwort;
	private String name;
	private Kennzahlen kennzahlen;
	private float fremdkapital;
	private float eigenkapital;

	private Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

	public Unternehmen( String name,String passwort, float fremdkapital, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.fremdkapital = fremdkapital;
		this.eigenkapital = eigenkapital;
	}

	/**
	 * Methode zum Initialisieren der Abteilungen.
	 */
	public void initDepartments()
	{
		abteilungen.put("marketing", new Marketing(kennzahlen));
		abteilungen.put("finanzen", new Finanzen(kennzahlen));
		abteilungen.put("FuE",new FuE(kennzahlen));
		abteilungen.put("produktion", new Produktion(kennzahlen));
		abteilungen.put("sozialeLeistungen", new SozialeLeistungen(kennzahlen));
		abteilungen.put("vertrieb", new Vertrieb(kennzahlen));

	}

	
	public Unternehmen(String name, String passwort, float fremdkapital) {
		this(name,passwort,fremdkapital,10000);
	}

	// Getter und Setter:
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
	public Abteilung getAbteilung(String key)
	{
		return abteilungen.get(key);
	}
	public Kennzahlen getKennzahlen() {
		return kennzahlen;
	}
}

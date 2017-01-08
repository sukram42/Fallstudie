package Unternehmung;

import Unternehmung.Abteilungen.*;

import java.util.HashMap;
import java.util.Map;

public class Unternehmen {

	private String passwort;
	private String name;
	private Kennzahlen kennzahlen;

	private Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

	public Unternehmen( String name,String passwort, float fremdkapital, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.kennzahlen = new Kennzahlen(eigenkapital, fremdkapital);
		initDepartments();
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
		abteilungen.put("vertrieb", new Vertrieb(kennzahlen, (Produktion) this.getAbteilung("produktion")));

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
	public Abteilung getAbteilung(String key)
	{
		return abteilungen.get(key);
	}
	public Kennzahlen getKennzahlen() {
		return kennzahlen;
	}
}

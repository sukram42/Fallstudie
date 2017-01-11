package Unternehmung;

import Unternehmung.Abteilungen.*;

import java.util.HashMap;
import java.util.Map;

public class Unternehmen {

	private String passwort;
	private String name;
	private Kennzahlensammlung kennzahlensammlung;

	private Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

	public Unternehmen( String name,String passwort, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.kennzahlensammlung = new Kennzahlensammlung(this,eigenkapital);
		initDepartments();
	}

	/**
	 * Methode zum Initialisieren der Abteilungen.
	 */
	public void initDepartments()
	{
		abteilungen.put("marketing", new Marketing(kennzahlensammlung));
		abteilungen.put("finanzen", new Finanzen(kennzahlensammlung));
		abteilungen.put("FuE",new FuE(kennzahlensammlung));
		abteilungen.put("produktion", new Produktion(kennzahlensammlung));
		abteilungen.put("sozialeLeistungen", new SozialeLeistungen(kennzahlensammlung));
		abteilungen.put("vertrieb", new Vertrieb(kennzahlensammlung, (Produktion) this.getAbteilung("produktion")));
		abteilungen.put("hr", new HR(this,kennzahlensammlung));

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
	public Map<String,Abteilung> getAbteilungen()
	{
		return abteilungen;
	}
	public Kennzahlensammlung getKennzahlensammlung() {
		return kennzahlensammlung;
	}
}

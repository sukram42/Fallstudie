package Unternehmung.Abteilungen;


import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;

/**
 * Abteilung Human Resources
 */
public class HR extends Abteilung {
	
	// TODO was soll hier passieren? - Ist es nicht besser Funktionen wie "Mitarbeiter entlassen", "Mitarbeiter von
	// TODO Abteilung X in Abteilung Y verschieben" oder "Gehalt bearbeiten" in der Klasse Mitarbeiter zu implementieren?

    /**
     * Konstruktor, zum Erstellen der Abteilung Human Resources
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Kennzahlen laufend fortzuschreiben / zu berechnen
     */
    public HR(Kennzahlen kennzahlen) {
        super(kennzahlen);
    }
}

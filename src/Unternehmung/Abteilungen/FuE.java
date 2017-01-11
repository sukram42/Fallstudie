package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;

/**
 * Klasse, die die Abteilung Forschung und Entwicklung repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class FuE extends Abteilung {

    /**
     * Konstruktor, zum Erstellen der Abteilung Forschung & Entwicklung
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public FuE(Kennzahlensammlung kennzahlensammlung) {
        super(kennzahlensammlung);
    }
}

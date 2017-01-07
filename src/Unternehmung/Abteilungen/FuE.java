package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;

/**
 * Klasse, die die Abteilung Forschung und Entwicklung repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class FuE extends Abteilung {

    /**
     * Konstruktor, zum Erstellen der Abteilung Forschung & Entwicklung
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Kennzahlen laufend fortzuschreiben / zu berechnen
     */
    public FuE(Kennzahlen kennzahlen) {
        super(kennzahlen);
    }
}

package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;

/**
 * Klasse, die die Abteilung Finanzen repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Finanzen extends Abteilung {

    private Kennzahlen kennzahlen; // Kennzahlenobjekt des Unternehmens benötigt, um Kennzahlen zu manipulieren

    /**
     * Konstruktor, zum Erstellen der Abteilung
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Kennzahlen laufend fortzuschreiben / zu berechnen
     */
    public Finanzen(Kennzahlen kennzahlen) {
        this.kennzahlen = kennzahlen;
    }
}

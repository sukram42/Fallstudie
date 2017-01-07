package Unternehmung;

import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Abteilungen.Vertrieb;

import java.util.HashMap;
import java.util.Map;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTest {
    public static void main(String[] args) {

        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("TestCo1", "kennwort", 100000, 100000);

        System.out.println("Unternehmen " + unternehmen1.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen1.getKennzahlen().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen1.getKennzahlen().getFremdkapital() + " gegründet.");

        // Mitarbeiter einstellen:
        unternehmen1.getAbteilung("produktion").addMitarbeiter(2, 25000);
        unternehmen1.getAbteilung("vertrieb").addMitarbeiter(2, 35000);

        // Maschinen kaufen:
        Produktion produktion = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion.maschinenKaufen("3", 1);

        // Rucksäcke produzieren:
        produktion.produzieren("Rucksack", 3000, 10);

        // Rucksäcke verkaufen:
        Vertrieb vertrieb = (Vertrieb) unternehmen1.getAbteilung("vertrieb");
        vertrieb.verkaufen("Rucksack", 50, 3000);

        // Jahresende:
        System.out.println("Das Unternehmen " + unternehmen1.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen1.getKennzahlen().getGewinn() + " € erzielt.");


    }
}

package Unternehmung;

import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Abteilungen.Vertrieb;

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
        produktion.maschinenKaufen("3", 2);

        // Rucksäcke produzieren:
        produktion.produzieren("Rucksack", 6000, 10);

        // Marketingkampagne und Marktforschung:
        Marketing marketing = (Marketing) unternehmen1.getAbteilung("marketing");
        marketing.marketingKampagne("Print");
        marketing.marketingKampagne("TV");
        marketing.marktforschung(2);

        // Rucksäcke verkaufen:
        Vertrieb vertrieb = (Vertrieb) unternehmen1.getAbteilung("vertrieb");
        vertrieb.verkaufen("Rucksack", 50);

        // Jahresende:
        System.out.println("Das Unternehmen " + unternehmen1.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen1.getKennzahlen().getGewinn() + " € erzielt.");
        System.out.println("Kosten: " + (unternehmen1.getKennzahlen().getSonstigeKosten() + unternehmen1.getKennzahlen().getGehälter() +
                unternehmen1.getKennzahlen().getHerstellkosten()));
        System.out.println("Umsatz: " + unternehmen1.getKennzahlen().getUmsatz());
        System.out.println("Bekanntheitsgrad: " + unternehmen1.getKennzahlen().getBekanntheitsgrad());
        System.out.println("Verkaufsrate: " + unternehmen1.getKennzahlen().getVerkaufsrate());

    }
}

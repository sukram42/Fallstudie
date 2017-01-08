package Unternehmung;

import Rules.Game;
import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Abteilungen.Vertrieb;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTest {
    public static void main(String[] args) {

        // ############################## UNTERNEHMEN 1 ##############################
        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("Unternehmen 1", "kennwort", 100000, 100000);

        System.out.println("Unternehmen " + unternehmen1.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen1.getKennzahlen().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen1.getKennzahlen().getFremdkapital() + " gegründet.");

        // Mitarbeiter einstellen:
        unternehmen1.getAbteilung("produktion").addMitarbeiter(2, 25000);
        unternehmen1.getAbteilung("vertrieb").addMitarbeiter(2, 35000);

        // Maschinen kaufen:
        Produktion produktion1 = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion1.maschinenKaufen("3", 2);

        // Rucksäcke produzieren:
        produktion1.produzieren("Rucksack", 6000, 10);

        // Marketingkampagne und Marktforschung:
        Marketing marketing1 = (Marketing) unternehmen1.getAbteilung("marketing");
        marketing1.marketingKampagne("Print");
        marketing1.marketingKampagne("TV");
        marketing1.marktforschung(2);

        // Rucksäcke verkaufen:
        Vertrieb vertrieb1 = (Vertrieb) unternehmen1.getAbteilung("vertrieb");
        vertrieb1.verkaufen("Rucksack", 50);


        // ############################## UNTERNEHMEN 2 ##############################
        // Unternehmen erstellen:
        Unternehmen unternehmen2 = new Unternehmen("Unternehmen 2", "kennwort", 100000, 100000);

        System.out.println("Unternehmen " + unternehmen2.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen2.getKennzahlen().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen2.getKennzahlen().getFremdkapital() + " gegründet.");

        // Mitarbeiter einstellen:
        unternehmen2.getAbteilung("produktion").addMitarbeiter(2, 25000);
        unternehmen2.getAbteilung("vertrieb").addMitarbeiter(2, 35000);

        // Maschinen kaufen:
        Produktion produktion2 = (Produktion) unternehmen2.getAbteilung("produktion");
        produktion2.maschinenKaufen("3", 3);

        // Rucksäcke produzieren:
        produktion2.produzieren("Rucksack", 9000, 15);

        // Marketingkampagne und Marktforschung:
        Marketing marketing2 = (Marketing) unternehmen2.getAbteilung("marketing");
        marketing2.marketingKampagne("Plakate");
        marketing2.marketingKampagne("Print");
        marketing2.marketingKampagne("Radio");
        marketing2.marketingKampagne("TV");
        marketing2.marktforschung(3);

        // Rucksäcke verkaufen:
        Vertrieb vertrieb2 = (Vertrieb) unternehmen2.getAbteilung("vertrieb");
        vertrieb2.verkaufen("Rucksack", 50);


        // ############################## JAHRESENDE ##############################
        // Unternehmen 1:
        System.out.println("Das Unternehmen " + unternehmen1.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen1.getKennzahlen().getGewinn() + " € erzielt.");
        System.out.println("Kosten: " + (unternehmen1.getKennzahlen().getSonstigeKosten() + unternehmen1.getKennzahlen().getGehälter() +
                unternehmen1.getKennzahlen().getHerstellkosten()));
        System.out.println("Umsatz: " + unternehmen1.getKennzahlen().getUmsatz());
        System.out.println("Bekanntheitsgrad: " + unternehmen1.getKennzahlen().getBekanntheitsgrad());
        System.out.println("Verkaufsrate: " + unternehmen1.getKennzahlen().getAbsatzrate());

        // Unternehmen 2:
        System.out.println("Das Unternehmen " + unternehmen2.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen2.getKennzahlen().getGewinn() + " € erzielt.");
        System.out.println("Kosten: " + (unternehmen2.getKennzahlen().getSonstigeKosten() + unternehmen1.getKennzahlen().getGehälter() +
                unternehmen2.getKennzahlen().getHerstellkosten()));
        System.out.println("Umsatz: " + unternehmen2.getKennzahlen().getUmsatz());
        System.out.println("Bekanntheitsgrad: " + unternehmen2.getKennzahlen().getBekanntheitsgrad());
        System.out.println("Verkaufsrate: " + unternehmen2.getKennzahlen().getAbsatzrate());

        // Gewinner ermitteln:
        Game game = new Game(unternehmen1, unternehmen2);
        System.out.println(game.gewinnerErmitteln().getName() + " ist der Gewinner!");

    }
}

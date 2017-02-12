import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterException;
import Exceptions.ZuWenigMitarbeiterOderMaschinenException;
import Rules.Game;
import Unternehmung.Abteilungen.*;
import Unternehmung.Unternehmen;

/**
 * Created by D064018 on 12.02.2017.
 */
public class SzenarioTest {

    public static void main(String[] args) {
        Game game = new Game();

        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("Unternehmen 1", "kennwort", 500000);
        Unternehmen unternehmen2 = new Unternehmen("Unternehmen 1", "kennwort", 500000);

        game.getCompanies().add(unternehmen1);
        game.getCompanies().add(unternehmen2);

        //Manager, Hersteller und Vertriebler hinzufügen
        try {
            unternehmen1.getAbteilung("hr").addMitarbeiter(1, 2500);
            unternehmen1.getAbteilung("produktion").addMitarbeiter(3, 2500);
            unternehmen1.getAbteilung("vertrieb").addMitarbeiter(2, 2500);

            unternehmen2.getAbteilung("hr").addMitarbeiter(1, 2500);
            unternehmen2.getAbteilung("produktion").addMitarbeiter(3, 2500);
            unternehmen2.getAbteilung("vertrieb").addMitarbeiter(2, 2500);
        } catch (ZuWenigMitarbeiterException e) {
            e.printStackTrace();
        }

        //Für Opportunities bewerben
        Vertrieb vertrieb1 = (Vertrieb)unternehmen1.getAbteilung("vertrieb");
        Vertrieb vertrieb2 = (Vertrieb)unternehmen2.getAbteilung("vertrieb");

        try{
            vertrieb1.bewerben(Game.getAusschreibungen().get(1));
            vertrieb2.bewerben(Game.getAusschreibungen().get(2));
        } catch (Exception e){
            e.printStackTrace();
        }

        // Produktions- und Lagerhalle kaufen:
        Produktion produktion1 = (Produktion) unternehmen1.getAbteilung("produktion");
        Produktion produktion2 = (Produktion) unternehmen1.getAbteilung("produktion");

        produktion1.produktionshalleKaufen(1);
        produktion1.lagerhalleKaufen(1);

        produktion2.produktionshalleKaufen(1);
        produktion2.lagerhalleKaufen(1);

        // Maschinen kaufen:
        try {
            produktion1.maschinenKaufen("Rucksack", 1, 1);
            produktion2.maschinenKaufen("Rucksack", 1, 1);
        } catch (ZuWenigMaschinenstellplatzException e) {
            e.printStackTrace();
        }

        // Rucksäcke produzieren:
        try{
            produktion1.produzieren("Rucksack", 'C', 100, 120);
            produktion2.produzieren("Rucksack", 'C', 100, 120);
        } catch (ZuWenigMitarbeiterOderMaschinenException e){
            e.printStackTrace();
        }

        // Forschung starten
        Forschung forschung1 = (Forschung) unternehmen1.getAbteilung("forschung");
        Forschung forschung2 = (Forschung) unternehmen2.getAbteilung("forschung");

        try {
            forschung1.addMitarbeiter(1, 2500);
            forschung2.addMitarbeiter(1, 2500);

            forschung1.starteProjekt("RucksackC", 1, 30, true);
            forschung2.starteProjekt("RucksackC", 1, 30, false);

            System.out.println("Forschungsprojekte gestartet");
        }catch (Exception e){
            e.printStackTrace();
        }


        //Marketingkampagne/-forschung starten
        Marketing marketing1 = (Marketing) unternehmen1.getAbteilung("marketing");
        Marketing marketing2 = (Marketing) unternehmen2.getAbteilung("marketing");

        try {
            marketing1.addMitarbeiter(1, 2500);
            marketing2.addMitarbeiter(1, 2500);

            marketing1.marketingkampagneStarten("Social Media", 3);
            marketing2.marktforschungStarten(1);

            System.out.println("Marketingprojekte gestartet");
        }catch (Exception e){
            e.printStackTrace();
        }

        //Kredit aufnehmen
        Finanzen finanzen1 = (Finanzen) unternehmen1.getAbteilung("finanzen");
        Finanzen finanzen2 = (Finanzen) unternehmen2.getAbteilung("finanzen");

        try {
            finanzen1.addMitarbeiter(1, 2500);
            finanzen2.addMitarbeiter(1, 2500);

            finanzen1.kreditAufnehmen(50000, 12);
            finanzen2.kreditAufnehmen(20000, 12);

            System.out.println("Kredite gestartet");
        }catch (Exception e){
            e.printStackTrace();
        }

        //Sozial Leistung starten
        HR hr1 = (HR) unternehmen1.getAbteilung("hr");
        HR hr2 = (HR) unternehmen2.getAbteilung("hr");

        try {
            hr1.startProjekt("wifi");
            hr2.startProjekt("urlaubsgeld");

            System.out.println("Soziale Leistungen gestartet");
        }catch (Exception e){
            e.printStackTrace();
        }

        //1 2/3 Monat vergehen lassen
        int i = 50;
        try{
        while (i > 0){
            game.run();
            System.out.println("Noch " + i + " Tage");
            i--;
        }}catch (Exception e){
            e.printStackTrace();
        }
//Unternehmen 1 ist pleite gegangen
    }
}

package Unternehmung;

import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterOderMaschinenException;
import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Abteilungen.SozialeLeistungen;
import Unternehmung.Abteilungen.Vertrieb;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTest {
    public static void main(String[] args) {

        // ############################## UNTERNEHMEN 1 ##############################
        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("Unternehmen 1", "kennwort", 1000000);

        System.out.println("Liquide Mittel " + unternehmen1.getKennzahlensammlung().getLiquideMittel());

        System.out.println("Unternehmen " + unternehmen1.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen1.getKennzahlensammlung().getBilanz().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen1.getKennzahlensammlung().getBilanz().getFremdkapital() + " gegründet.");


        unternehmen1.getAbteilung("hr").addMitarbeiter(2,2500);
        System.out.println("Mitarbeiterzufriedenheit : " + unternehmen1.getKennzahlensammlung().getMitarbeiterzufriedenheit().berechnen());

        ((SozialeLeistungen)unternehmen1.getAbteilung("sozialeLeistungen")).startProjekt("kantine");
        System.out.println("Mitarbeiterzufriedenheit mit Kantine: " + unternehmen1.getKennzahlensammlung().getMitarbeiterzufriedenheit().berechnen());
        System.out.println("Kundenzufriedenheit: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("kundenzufriedenheit").berechnen());
        System.out.println("Image: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("image").berechnen());
        System.out.println("Produktqualität: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("produktqualität").berechnen());



        // Mitarbeiter einstellen:
        unternehmen1.getAbteilung("produktion").addMitarbeiter(1, 25000);
        //unternehmen1.getAbteilung("vertrieb").addMitarbeiter(1, 35000);

        // Produktions- und Lagerhalle kaufen:
        Produktion produktion1 = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion1.produktionshalleKaufen(1);
        produktion1.lagerhalleKaufen(2);

        // Maschinen kaufen:
        try {
            produktion1.maschinenKaufen("Rucksack", 3, 1);
        } catch (ZuWenigMaschinenstellplatzException e){
            e.printStackTrace();
        }

        // Rucksäcke produzieren:
        try{
            produktion1.produzieren("Rucksack", 'C', 10, 12);
        } catch (ZuWenigMitarbeiterOderMaschinenException e) {
            e.printStackTrace();
        }

        // Marketingkampagne und Marktforschung:
        Marketing marketing1 = (Marketing) unternehmen1.getAbteilung("marketing");
//        marketing1.marketingKampagne("Print");
//        marketing1.marketingKampagne("TV");
//        marketing1.marktforschung(2);

        // Rucksäcke verkaufen:
        //Vertrieb vertrieb1 = (Vertrieb) unternehmen1.getAbteilung("vertrieb");
        //vertrieb1.verkaufen("Rucksack", 55);


        // ############################## UNTERNEHMEN 2 ##############################
        // Unternehmen erstellen:
        Unternehmen unternehmen2 = new Unternehmen("Unternehmen 2", "kennwort", 1000000);

        System.out.println("Unternehmen " + unternehmen2.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen2.getKennzahlensammlung().getBilanz().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen2.getKennzahlensammlung().getBilanz().getFremdkapital() + " gegründet.");

        // Mitarbeiter einstellen:
        unternehmen2.getAbteilung("produktion").addMitarbeiter(10, 25000);
        //unternehmen2.getAbteilung("vertrieb").addMitarbeiter(1, 35000);

        // Produktions- und Lagerhalle kaufen:
        Produktion produktion2 = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion2.produktionshalleKaufen(1);
        produktion2.lagerhalleKaufen(2);

        // Maschinen kaufen:
        try {
            produktion2.maschinenKaufen("Rucksack", 3, 1);
        } catch (ZuWenigMaschinenstellplatzException e){
            e.printStackTrace();
        }

        // Rucksäcke produzieren:
        try{
            produktion2.produzieren("Rucksack", 'C', 10, 12);
        } catch (ZuWenigMitarbeiterOderMaschinenException e){
            e.printStackTrace();
        }

        // Marketingkampagne und Marktforschung:
        Marketing marketing2 = (Marketing) unternehmen2.getAbteilung("marketing");
//        marketing2.marketingKampagne("Plakate");
//        marketing2.marketingKampagne("Radio");
//        marketing2.marktforschung(2);

        // Rucksäcke verkaufen:
        //Vertrieb vertrieb2 = (Vertrieb) unternehmen2.getAbteilung("vertrieb");
        //vertrieb2.verkaufen("Rucksack", 40);


        // ############################## JAHRESENDE ##############################
        // Unternehmen 1:
        System.out.println("Das Unternehmen " + unternehmen1.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen1.getKennzahlensammlung().getGuv().jahresUeberschussBerechnen() + " € erzielt.");
        //System.out.println("Kosten: " + (unternehmen1.getKennzahlensammlung().getSonstigeKosten() + unternehmen1.getKennzahlensammlung().getGehälter() +
                //unternehmen1.getKennzahlensammlung().getHerstellkosten()));
        //System.out.println("Umsatz: " + unternehmen1.getKennzahlensammlung().getBilanz().getGuv().getUmsatz());
        System.out.println("Bekanntheitsgrad: " + unternehmen1.getKennzahlensammlung().getBekanntheitsgrad());
        System.out.println("Verkaufsrate: " + unternehmen1.getKennzahlensammlung().getAbsatzrate());

        // Unternehmen 2:
        System.out.println("Das Unternehmen " + unternehmen2.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
                unternehmen2.getKennzahlensammlung().getGuv().jahresUeberschussBerechnen() + " € erzielt.");
        //System.out.println("Kosten: " + (unternehmen2.getKennzahlensammlung().getSonstigeKosten() + unternehmen1.getKennzahlensammlung().getGehälter() +
                //unternehmen2.getKennzahlensammlung().getHerstellkosten()));
        //System.out.println("Umsatz: " + unternehmen2.getKennzahlensammlung().getUmsatz());
        System.out.println("Bekanntheitsgrad: " + unternehmen2.getKennzahlensammlung().getBekanntheitsgrad());
        System.out.println("Verkaufsrate: " + unternehmen2.getKennzahlensammlung().getAbsatzrate());

        // Gewinner ermitteln:
//        Game game = new Game(unternehmen1, unternehmen2);
//        System.out.println(game.gewinnerErmitteln().getName() + " ist der Gewinner!");

        System.out.println("Liquide Mittel " + unternehmen1.getKennzahlensammlung().getLiquideMittel());

    }
}

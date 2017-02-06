package Unternehmung;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Abteilungen.SozialeLeistungen;
import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Kennzahlen.GuV;
import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTestklasse {
    public static void main(String[] args) {
        Game game = new Game();
        // ############################## UNTERNEHMEN 1 ##############################
        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("Unternehmen 1", "kennwort", 1000000000);

        game.getCompanies().add(unternehmen1);

        System.out.println("Liquide Mittel " + unternehmen1.getKennzahlensammlung().getBilanz().getLiquideMittel());

        System.out.println("Unternehmen " + unternehmen1.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen1.getKennzahlensammlung().getBilanz().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen1.getKennzahlensammlung().getBilanz().getFremdkapital() + " gegründet.");

        try {
            unternehmen1.getAbteilung("hr").addMitarbeiter(1, 2500);
            System.out.println("Mitarbeiterzufriedenheit : " + unternehmen1.getKennzahlensammlung().getMitarbeiterzufriedenheit().berechnen());
        } catch (ZuWenigMitarbeiterException e){
            e.printStackTrace();
        }

        ((SozialeLeistungen)unternehmen1.getAbteilung("sozialeLeistungen")).startProjekt("kantine");
        System.out.println("Mitarbeiterzufriedenheit mit Kantine: " + unternehmen1.getKennzahlensammlung().getMitarbeiterzufriedenheit().berechnen());
        System.out.println("Kundenzufriedenheit: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("kundenzufriedenheit").berechnen());
        System.out.println("Image: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("image").berechnen());

        for(int i = 0; i < 40;i++)
        {
            game.run();
        }
        System.out.println(new Gson().toJson(unternehmen1.getKennzahlensammlung().getGuv().getArchiv()));

        // Mitarbeiter einstellen:
        try {
//            unternehmen1.getAbteilung("hr").addMitarbeiter(5, 25000);
//            unternehmen1.getAbteilung("produktion").addMitarbeiter(1, 25000);
            unternehmen1.getAbteilung("vertrieb").addMitarbeiter(1, 25000);
        } catch (ZuWenigMitarbeiterException e){
            e.printStackTrace();
        }
        //unternehmen1.getAbteilung("vertrieb").addMitarbeiter(1, 35000);


        Vertrieb vertrieb = (Vertrieb)unternehmen1.getAbteilung("vertrieb");
        try {
            System.out.print("SIZE : "+ game.getAusschreibungen().size());
            vertrieb.bewerben(Game.getAusschreibungen().get(0));
            System.out.println(new Gson().toJson(vertrieb.getOpportunities()));
        } catch (ZuWenigMitarbeiterException e) {
            e.printStackTrace();
        }



        // Marketingkampagne und Marktforschung:
        Marketing marketing1 = (Marketing) unternehmen1.getAbteilung("marketing");
        try {
            marketing1.addMitarbeiter(1,1);
            marketing1.marketingkampagneStarten("Social",1);
            game.run();
            game.run();
            System.out.println(Game.getTimeString());
        } catch (ZuWenigMitarbeiterException e) {
            e.printStackTrace();
        }
//        marketing1.marketingKampagne("Print");
//        marketing1.marketingKampagne("TV");
//        marketing1.marktforschung(2);

        // Rucksäcke verkaufen:
        //Vertrieb vertrieb1 = (Vertrieb) unternehmen1.getAbteilung("vertrieb");
        //vertrieb1.verkaufen("Rucksack", 55);


        // ############################## UNTERNEHMEN 2 ##############################
//        // Unternehmen erstellen:
//        Unternehmen unternehmen2 = new Unternehmen("Unternehmen 2", "kennwort", 1000000);
//
//        System.out.println("Unternehmen " + unternehmen2.getName() + " mit Eigenkapital in Höhe von " +
//                unternehmen2.getKennzahlensammlung().getBilanz().getEigenkapital() + " und Fremdkapital in Höhe von " +
//                unternehmen2.getKennzahlensammlung().getBilanz().getFremdkapital() + " gegründet.");
//
//        // Mitarbeiter einstellen:
//        try {
//
//            unternehmen2.getAbteilung("hr").addMitarbeiter(10, 25000);
//            unternehmen2.getAbteilung("produktion").addMitarbeiter(10, 25000);
//            //unternehmen2.getAbteilung("vertrieb").addMitarbeiter(1, 35000);
//        } catch (ZuWenigMitarbeiterException e){
//            e.printStackTrace();
//        }
//
//        // Produktions- und Lagerhalle kaufen:
//        Produktion produktion2 = (Produktion) unternehmen1.getAbteilung("produktion");
//        produktion2.produktionshalleKaufen(1);
//        produktion2.lagerhalleKaufen(2);
//
//        // Maschinen kaufen:
//        try {
//            produktion2.maschinenKaufen("Rucksack", 3, 1);
//        } catch (ZuWenigMaschinenstellplatzException e){
//            e.printStackTrace();
//        }
//
//        // Rucksäcke produzieren:
//        try{
//            produktion2.produzieren("Rucksack", 'C', 10, 12);
//        } catch (ZuWenigMitarbeiterOderMaschinenException e){
//            e.printStackTrace();
//        }
//
//        // Marketingkampagne und Marktforschung:
//        Marketing marketing2 = (Marketing) unternehmen2.getAbteilung("marketing");
//        marketing2.marketingKampagne("Plakate");
//        marketing2.marketingKampagne("Radio");
//        marketing2.marktforschung(2);

        // Rucksäcke verkaufen:
        //Vertrieb vertrieb2 = (Vertrieb) unternehmen2.getAbteilung("vertrieb");
        //vertrieb2.verkaufen("Rucksack", 40);


        // ############################## JAHRESENDE ##############################
        unternehmen1.getKennzahlensammlung().getGuv().jahresabschluss(unternehmen1.getKennzahlensammlung().getBilanz());
//        unternehmen2.getKennzahlensammlung().getGuv().jahresabschluss(unternehmen1.getKennzahlensammlung().getBilanz());

//        // Unternehmen 1:
//        System.out.println("Das Unternehmen " + unternehmen1.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
//                unternehmen1.getKennzahlensammlung().getGuv().getJahresUeberschuss() + " € erzielt.");
//        //System.out.println("Kosten: " + (unternehmen1.getKennzahlensammlung().getSonstigeKosten() + unternehmen1.getKennzahlensammlung().getGehälter() +
//                //unternehmen1.getKennzahlensammlung().getHerstellkosten()));
//        //System.out.println("Umsatz: " + unternehmen1.getKennzahlensammlung().getBilanz().getGuv().getUmsatz());
//        System.out.println("Bekanntheitsgrad: " + unternehmen1.getKennzahlensammlung().getBekanntheitsgrad());
//        System.out.println("Verkaufsrate: " + unternehmen1.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert());
//
//        // Unternehmen 2:
//        System.out.println("Das Unternehmen " + unternehmen2.getName() + " hat in diesem Geschäftsjahr ein Ergebnis von " +
//                unternehmen2.getKennzahlensammlung().getGuv().getJahresUeberschuss() + " € erzielt.");
//        //System.out.println("Kosten: " + (unternehmen2.getKennzahlensammlung().getSonstigeKosten() + unternehmen1.getKennzahlensammlung().getGehälter() +
//                //unternehmen2.getKennzahlensammlung().getHerstellkosten()));
//        //System.out.println("Umsatz: " + unternehmen2.getKennzahlensammlung().getUmsatz());
//        System.out.println("Bekanntheitsgrad: " + unternehmen2.getKennzahlensammlung().getBekanntheitsgrad());
//        System.out.println("Verkaufsrate: " + unternehmen2.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert());
//
//        // Gewinner ermitteln:
////        Game game = new Game(unternehmen1, unternehmen2);
////        System.out.println(game.gewinnerErmitteln().getName() + " ist der Gewinner!");
//
//        System.out.println("Liquide Mittel " + unternehmen1.getKennzahlensammlung().getBilanz().getLiquideMittel());

    }
}

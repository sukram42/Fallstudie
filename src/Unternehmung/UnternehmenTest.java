package Unternehmung;

import Unternehmung.Abteilungen.Marketing;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTest {
    public static void main(String[] args) {
        Unternehmen unternehmen = new Unternehmen("TestCo", "kennwort", 100000, 100000);




        /* // Mitarbeiterzufriedenheit:
        float gesamtesGehalt = 100000;
        Kennzahl mitarbeiterzufriedenheit = new Kennzahl((gesamtesGehalt/(float) hr.mitarbeiter.size()/ 70000), (float) 1.02);
        System.out.println("Mitarbeiterzufriedenheit: " + mitarbeiterzufriedenheit.getWert());
        mitarbeiterzufriedenheit.wertBerechnen(gesamtesGehalt/(float) hr.mitarbeiter.size(), (float) 1.5);
        System.out.println("neue Mitarbeiterzufriedenheit: " + mitarbeiterzufriedenheit.getWert());
        */



        /* // Mitarbeiter hinzufügen:
        FuE FuE = new FuE();
        FuE.mitarbeiter.add(new Mitarbeiter("Musterlurch", "Tom", "Musteradresse", "bild", 'm'));
        for (int i = 0; i < FuE.mitarbeiter.size(); i++) {
            System.out.println(FuE.mitarbeiter.get(i).getVorname() + " " + FuE.mitarbeiter.get(i).getName());
        }
        */
    }
}

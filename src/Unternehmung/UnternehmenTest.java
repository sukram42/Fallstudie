package Unternehmung;

import Unternehmung.Abteilungen.Marketing;

/**
 * nur eine Testklasse!
 * Created by lucadommes on 28.12.2016.
 */
public class UnternehmenTest {
    public static void main(String[] args) {
        Unternehmen unternehmen1 = new Unternehmen("TestCo", "kennwort", 100000, 100000);

        System.out.println("Unternehmen " + unternehmen1.getName() + " mit Eigenkapital in Höhe von " +
                unternehmen1.getKennzahlen().getEigenkapital() + " und Fremdkapital in Höhe von " +
                unternehmen1.getKennzahlen().getFremdkapital() + " gegründet.");


        /* // Mitarbeiter hinzufügen:
        FuE FuE = new FuE();
        FuE.mitarbeiter.add(new Mitarbeiter("Musterlurch", "Tom", "Musteradresse", "bild", 'm'));
        for (int i = 0; i < FuE.mitarbeiter.size(); i++) {
            System.out.println(FuE.mitarbeiter.get(i).getVorname() + " " + FuE.mitarbeiter.get(i).getName());
        }
        */
    }
}

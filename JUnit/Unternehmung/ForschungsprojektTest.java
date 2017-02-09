package Unternehmung;

import Unternehmung.Abteilungen.Forschung;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Kennzahlen.Kennzahlensammlung;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 23.01.2017.
 */
public class ForschungsprojektTest {

    private Unternehmen unternehmen;
    private Produktion produktion;
    private Forschung forschung;
    private Forschungsprojekt testForschungsprojekt;
    private Kennzahlensammlung kennzahlensammlung;

    @Before
    public void setUp() throws Exception{
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        produktion = (Produktion)unternehmen.getAbteilung("produktion");
        forschung = (Forschung)unternehmen.getAbteilung("forschung");
        unternehmen.getAbteilung("hr").addMitarbeiter(1, 1000);
        forschung.addMitarbeiter(5, 10000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testForschungsprojekt = new Forschungsprojekt(kennzahlensammlung, forschung, "RucksacktechA", 5, 50, true);
        assertNotNull(testForschungsprojekt);
    }

    @Ignore //Wert muss noch angepasst werden
    public void abbrechen1() throws Exception {
        testForschungsprojekt.abbrechen();
        assertEquals(produktion.getForschungsbonusById("RucksacktechA"), 2, 0.5);
    }

    @Ignore //Wert muss noch angepasst werden
    public void abschließen1() throws Exception {
        testForschungsprojekt.abschließen();
        assertEquals(produktion.getForschungsbonusById("RucksacktechA"), 3, 0.5);
    }

    @Ignore //Wert muss noch angepasst werden
    public void abbrechen2() throws Exception {
        testForschungsprojekt = new Forschungsprojekt(kennzahlensammlung, forschung, "RucksacktechA", 5, 50, false);
        testForschungsprojekt.abbrechen();
        assertEquals(forschung.getImagebonusById("RucksacktechA"), 1, 0.5);
    }

    @Ignore //Wert muss noch angepasst werden
    public void abschließen2() throws Exception {
        testForschungsprojekt = new Forschungsprojekt(kennzahlensammlung, forschung, "RucksacktechA", 5, 50, false);
        testForschungsprojekt.abschließen();
        assertEquals(forschung.getImagebonusById("RucksacktechA"), 2, 0.5);
    }


    @Test
    public void getEnde() throws Exception {
assertNotNull(testForschungsprojekt.getEnde());
    }

    @Test
    public void getForschungsobjekt() throws Exception {
        assertEquals(testForschungsprojekt.getForschungsobjekt(), "RucksacktechA");
    }

     @Test
     public void feuereMitarbeiter() throws Exception{
        testForschungsprojekt.feuereMitarbeiter();
        assertEquals(testForschungsprojekt.getMitarbeiterAnzahl(), 4);
     }

    @Test
    public void getMitarbeiterAnzahl() throws Exception {
assertEquals(testForschungsprojekt.getMitarbeiterAnzahl(), 5);
    }

}
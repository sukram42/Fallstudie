package Unternehmung;

import Unternehmung.Abteilungen.Forschung;
import Unternehmung.Abteilungen.Produktion;
import org.junit.Before;
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

    @Before
    public void setUp() throws Exception{
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        produktion = (Produktion)unternehmen.getAbteilung("produktion");
        forschung = (Forschung)unternehmen.getAbteilung("forschung");
        forschung.addMitarbeiter(5, 10000);
        testForschungsprojekt = new Forschungsprojekt(produktion, forschung, "RucksacktechA", 5, 50, true);
        assertNotNull(testForschungsprojekt);
    }

    @Test
    public void abbrechen1() throws Exception {
        testForschungsprojekt.abbrechen();
        assertEquals(produktion.getForschungsbonusById("RucksacktechA"), 2, 0.5);
    }

    @Test
    public void abschließen1() throws Exception {
        testForschungsprojekt.abschließen();
        assertEquals(produktion.getForschungsbonusById("RucksacktechA"), 3, 0.5);
    }

    @Test
    public void abbrechen2() throws Exception {
        testForschungsprojekt = new Forschungsprojekt(produktion, forschung, "RucksacktechA", 5, 50, false);
        testForschungsprojekt.abbrechen();
        assertEquals(forschung.getImagebonusById("RucksacktechA"), 1, 0.5);
    }

    @Test
    public void abschließen2() throws Exception {
        testForschungsprojekt = new Forschungsprojekt(produktion, forschung, "RucksacktechA", 5, 50, false);
        testForschungsprojekt.abschließen();
        assertEquals(forschung.getImagebonusById("RucksacktechA"), 2, 0.5);
    }


    @Test
    public void getBeginn() throws Exception {
assertNotNull(testForschungsprojekt.getBeginn());
    }

    @Test
    public void getForschungsobjekt() throws Exception {
        assertEquals(testForschungsprojekt.getForschungsobjekt(), "RucksacktechA");
    }

    @Test
    public void getMitarbeiterAnzahl() throws Exception {
assertEquals(testForschungsprojekt.getMitarbeiterAnzahl(), 5);
    }

}
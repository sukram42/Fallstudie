package Unternehmung;

import Unternehmung.Abteilungen.Produktion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 16.01.2017.
 */
public class AbteilungTest {

    String name;
    float aktKosten;
    Kennzahlensammlung kennzahlensammlung;
    ArrayList<Mitarbeiter> mitarbeiter;
    Abteilung testAbteilung;


    public void setUp(){
        aktKosten = 0;
        kennzahlensammlung = null;
        mitarbeiter = null;
    }

    @Before
    public void testCreateAbteilung(){
        Unternehmen testUnternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = testUnternehmen.getKennzahlensammlung();
        testAbteilung = new Produktion(kennzahlensammlung);
        assertNotNull(testAbteilung);
    }


    @Test
    public void addMitarbeiter() throws Exception {

    }

    @Test
    public void getMitarbeiter() throws Exception {

    }

    @Test
    public void getMitarbeiterAnzahl() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getKosten() throws Exception {

    }

    @Test
    public void getMitarbeiterKosten() throws Exception {

    }

}
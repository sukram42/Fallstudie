package Unternehmung;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by D064018 on 16.01.2017.
 */
public class AbteilungTest extends TestCase {

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
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testAbteilung = unternehmen.getAbteilung("produktion");
        assertNotNull(testAbteilung);
    }


    @Test
    public void addMitarbeiter() throws Exception {
        testAbteilung.addMitarbeiter(1, 10000);
        assertEquals(testAbteilung.getMitarbeiterAnzahl(), 1);
    }

    @Test
    public void getMitarbeiter() throws Exception {
        testAbteilung.addMitarbeiter(1, 10000);
        assertNotNull(testAbteilung.getMitarbeiter().get(1));
    }

    @Test
    public void getMitarbeiterAnzahl() throws Exception {
        assertEquals(testAbteilung.getMitarbeiterAnzahl(), 0);
    }

    @Test
    public void getKosten() throws Exception {
        assertEquals(testAbteilung.getKosten(), 0);
    }

    @Test
    public void getMitarbeiterKosten() throws Exception {
        testAbteilung.addMitarbeiter(2, 10000);
        assertEquals(testAbteilung.getMitarbeiterKosten(), 2 * 10000);
    }

}
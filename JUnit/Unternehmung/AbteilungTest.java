package Unternehmung;

import Exceptions.ZuWenigMitarbeiterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 16.01.2017.
 */
public class AbteilungTest{

    String name;
    float aktKosten;
    Kennzahlensammlung kennzahlensammlung;
    ArrayList<Mitarbeiter> mitarbeiter;
    Abteilung testAbteilung;

    @Before
    public void setUp(){
        aktKosten = 0;
        kennzahlensammlung = null;
        mitarbeiter = null;

        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testAbteilung = unternehmen.getAbteilung("produktion");
        assertNotNull(testAbteilung);
    }

    @Test
    public void addMitarbeiter(){
        try {
            testAbteilung.addMitarbeiter(1, 10000);
        } catch (ZuWenigMitarbeiterException e){
            e.printStackTrace();
        }
        assertEquals(testAbteilung.getMitarbeiterAnzahl(), 1);
    }

    @Test
    public void getMitarbeiter() {
        try{
        testAbteilung.addMitarbeiter(1, 10000);
        } catch (ZuWenigMitarbeiterException e) {
            assertNotNull(testAbteilung.getMitarbeiter().get(0));
        }
    }

    @Test
    public void getMitarbeiterAnzahl() {
        assertEquals(testAbteilung.getMitarbeiterAnzahl(), 0);
    }

    @Test
    public void getKosten(){
        float a = 0;
        assertEquals(testAbteilung.getKosten(), a, 0.5);
    }

    @Test
    public void getMitarbeiterKosten(){
        try{
        testAbteilung.addMitarbeiter(2, 10000);
        assertEquals(testAbteilung.getMitarbeiterKosten(), (2 * 10000), 0.5);
        } catch (ZuWenigMitarbeiterException e) {
        }
    }

    @After
    public void tearDown(){

    }

}
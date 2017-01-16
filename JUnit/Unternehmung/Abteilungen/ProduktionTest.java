package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Mitarbeiter;
import Unternehmung.Unternehmen;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 11.01.2017.
 */
public class ProduktionTest {

    String name;
    float aktKosten;
    Kennzahlensammlung kennzahlensammlung;
    ArrayList<Mitarbeiter> mitarbeiter;
    Abteilung testProduktion;


    public void setUp(){
        name = "produktion";
        aktKosten = 0;
        kennzahlensammlung = null;
        mitarbeiter = null;
    }

    @Before
    public void testCreateProduktion() {
        Unternehmen testUnternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = testUnternehmen.getKennzahlensammlung();
        testProduktion = new Produktion(kennzahlensammlung);
        assertNotNull(testProduktion);
    }

    @Test
    public void maschinenKaufen() throws Exception {

    }

    @org.junit.After
    public void maschineVerkaufen() throws Exception {

    }


    @Test
    public void produzieren() throws Exception {

    }

    @Test
    public void bestandVerändern() throws Exception {

    }

    @Test
    public void getProduzierteProdukte() throws Exception {

    }

    @Test
    public void getMaschinen() throws Exception {

    }

    @Test
    public void getMaschinenKapazität() throws Exception {

    }

}
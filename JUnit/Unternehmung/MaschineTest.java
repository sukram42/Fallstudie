package Unternehmung;

import Unternehmung.Kennzahlen.Kennzahlensammlung;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 11.01.2017.
 */

public class MaschineTest {

    private Maschine testMaschine;

    @Before
    public void setUp() {
        testMaschine = new Maschine("Rucksack", 2);
        assertNotNull(testMaschine);
    }

    @Test
    public void testGetKlasse() {
        assertEquals(testMaschine.getKlasse(), 2);
    }

    @Test
    public void testgetKapazitaet() {
        assertEquals(testMaschine.getKapazitaet(), 600);
    }

    @Test
    public void testGetAnschaffungskst() {
        assertEquals(testMaschine.getAnschaffungskst(), 15000);
    }

    @Test
    public void statusUndEnergiekstRuntersetzen() throws Exception {
        testMaschine.statusUndEnergiekstRuntersetzen();
        assertEquals(testMaschine.getEnergiekosten(), 100 * 1.000962, 0.5);
    }

    @Test
    public void getKlasse() throws Exception {
        assertEquals(testMaschine.getKlasse(), 2);
    }

    @Test
    public void getKapazitaet() throws Exception {
        assertEquals(testMaschine.getKapazitaet(), 600);
    }

    @Test
    public void getAnschaffungskst() throws Exception {
        assertEquals(testMaschine.getAnschaffungskst(), 15000);
    }

    @Test
    public void getProdukt() throws Exception {
        assertEquals(testMaschine.getProdukt(), "Rucksack");
    }

    @Test
    public void reparieren() {
        Unternehmen Unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        Kennzahlensammlung kennzahlensammlung = Unternehmen.getKennzahlensammlung();
        // testMaschine.setStatus(0.5);
        // testMaschine.reparieren(kenn);
        //ToDo überprüfen
    }

    @Test
    public void getStatus() {
        assertEquals(testMaschine.getStatus(), 1, 0.5);
    }

    @Test
    public void setStatus() {
        testMaschine.setStatus(0.5);
        assertEquals(testMaschine.getStatus(), 0.5, 0.5);
    }

    @Test
    public void getEnergiekosten() {
        assertEquals(testMaschine.getEnergiekosten(), 100f, 0.5);
    }

    @After
    public void tearDown() {

    }

}
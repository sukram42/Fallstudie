package Unternehmung.Kennzahlen;

import Unternehmung.Abteilungen.HR;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by D064018 on 23.01.2017.
 */
public class GuVTest {

    private Unternehmen unternehmen;
    private GuV testGuV;

    @Before
    public void setUp() {
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        testGuV = new GuV(unternehmen);
    }

    @Test
    public void jahresabschluss() throws Exception {
        Bilanz bilanz = new Bilanz(unternehmen);
        testGuV.setAufwendungenFuerEnergie(1000);
        testGuV.jahresabschluss(bilanz);
        assertEquals(bilanz.getEigenkapital(), 100000 - 1000, 0.5);
    }

    @Test
    public void importAufwandUndErlös() throws Exception {
        HR hr = (HR) unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(2, 1000);
        testGuV.importAufwandUndErlös();
        float gehalt = (float) 2000 / 12;
        assertEquals(testGuV.getAufwendungenFuerGehaelter(), gehalt, 0.5);
    }

    @Test
    public void getTaeglicheLiquiditätsveränderung() throws Exception {
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
        produktion.produktionshalleKaufen(2);
        produktion.maschinenKaufen("Rucksack", 1, 1);
        assertEquals(testGuV.getTaeglicheLiquiditätsveränderung(), -100, 0.5);
    }

    @Test
    public void addZinsaufwendungen() throws Exception {
        testGuV.addZinsaufwendungen(100);
        assertEquals(testGuV.getZinsaufwendungen(), 100, 0.5);
    }

    @Test
    public void addFremdinstandhaltung() throws Exception {
        testGuV.addFremdinstandhaltung(100);
        assertEquals(testGuV.getFremdinstandhaltung(), 100, 0.5);
    }

    @Test
    public void addUmsatz() throws Exception {
        testGuV.addUmsatz(100);
        assertEquals(testGuV.getUmsatzErloese(), 100, 0.5);
    }

    @Test
    public void addGeleisteterSchandsersatz() throws Exception {
        testGuV.addGeleisteterSchandsersatz(100);
        assertEquals(testGuV.getGeleisteterSchadensersatz(), 100, 0.5);
    }

    @Test
    public void getAufwendungenFuerWerbung() throws Exception {
        assertEquals(testGuV.getAufwendungenFuerWerbung(), 0, 0.5);
    }

    @Test
    public void setAufwendungenFuerWerbung() throws Exception {
        testGuV.setAufwendungenFuerWerbung(100);
        assertEquals(testGuV.getAufwendungenFuerWerbung(), 100, 0.5);
    }

    @Test
    public void getAufwendungenFuerGehaelter() throws Exception {
        assertEquals(testGuV.getAufwendungenFuerGehaelter(), 0, 0.5);

    }

    @Test
    public void setAufwendungenFuerGehaelter() throws Exception {
        testGuV.setAufwendungenFuerGehaelter(100);
        assertEquals(testGuV.getAufwendungenFuerGehaelter(), 100, 0.5);

    }

    @Test
    public void getAufwendungenFuerEnergie() throws Exception {
        assertEquals(testGuV.getAufwendungenFuerEnergie(), 0, 0.5);
    }

    @Test
    public void setAufwendungenFuerEnergie() throws Exception {
        testGuV.setAufwendungenFuerEnergie(100);
        assertEquals(testGuV.getAufwendungenFuerEnergie(), 100, 0.5);

    }

    @Test
    public void getUmsatzErlöse() throws Exception {
        assertEquals(testGuV.getUmsatzErloese(), 0, 0.5);

    }

    @Test
    public void setUmsatzErlöse() throws Exception {
        testGuV.setUmsatzErloese(100);
        assertEquals(testGuV.getUmsatzErloese(), 100, 0.5);

    }

    @Test
    public void getJahresUeberschuss() throws Exception {
        assertEquals(testGuV.getJahresUeberschuss(), 0, 0.5);

    }

    @Test
    public void setJahresUeberschuss() throws Exception {
        testGuV.setJahresUeberschuss(100);
        assertEquals(testGuV.getJahresUeberschuss(), 100, 0.5);

    }

    @Test
    public void getZinsaufwendungen() throws Exception {
        assertEquals(testGuV.getZinsaufwendungen(), 0, 0.5);
    }

    @Test
    public void setZinsaufwendungen() throws Exception {
        testGuV.setZinsaufwendungen(100);
        assertEquals(testGuV.getZinsaufwendungen(), 100, 0.5);

    }

    @Test
    public void getFremdinstandhaltung() throws Exception {
        assertEquals(testGuV.getFremdinstandhaltung(), 0, 0.5);
    }

    @Test
    public void setFremdinstandhaltung() throws Exception {
        testGuV.setFremdinstandhaltung(100);
        assertEquals(testGuV.getFremdinstandhaltung(), 100, 0.5);

    }

    @Test
    public void getGeleisteterSchadensersatz() throws Exception {
        assertEquals(testGuV.getGeleisteterSchadensersatz(), 0, 0.5);

    }

    @Test
    public void setGeleisteterSchadensersatz() throws Exception {
        testGuV.setGeleisteterSchadensersatz(100);
        assertEquals(testGuV.getGeleisteterSchadensersatz(), 100, 0.5);

    }

    @After
    public void tearDown() {

    }
}
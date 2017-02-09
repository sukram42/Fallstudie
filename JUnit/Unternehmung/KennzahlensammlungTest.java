package Unternehmung;

import Unternehmung.Kennzahlen.Kennzahlensammlung;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by D064018 on 23.01.2017.
 */
public class KennzahlensammlungTest {


    private Kennzahlensammlung testKennzahlensammlung;
    private Unternehmen unternehmen;
    private String kennzahl;

    @Before
    public void setUp(){
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        testKennzahlensammlung = new Kennzahlensammlung(unternehmen, 500000);
        testKennzahlensammlung.initWeicheKennzahlen();
        kennzahl = "mitarbeiterzufriedenheit";
    }

    @Test
    public void getMaxNeueMitarbeiter() throws Exception {
        unternehmen.getAbteilung("hr").addMitarbeiter(1, 1000);
        assertEquals(unternehmen.getKennzahlensammlung().getMaxNeueMitarbeiter(), 9);
    }

    @Test
    public void setMaxNeueMitarbeiter() throws Exception {
testKennzahlensammlung.setMaxNeueMitarbeiter(5);
assertEquals(testKennzahlensammlung.getMaxNeueMitarbeiter(), 5);
    }

    @Test
    public void initWeicheKennzahlen() throws Exception {
assertNotNull(testKennzahlensammlung.getWeicheKennzahl(kennzahl));
    }

    @Test
    public void getWeicheKennzahl() throws Exception {
assertNotNull(testKennzahlensammlung.getWeicheKennzahl(kennzahl));
    }

    @Test
    public void getMitarbeiterzufriedenheit() throws Exception {
        assertNotNull(testKennzahlensammlung.getMitarbeiterzufriedenheit());
    }

    @Test
    public void getMarktanteil() throws Exception {
        assertEquals(testKennzahlensammlung.getMarktanteil(), 0, 0.5);
    }

    @Test
    public void setMarktanteil() throws Exception {
testKennzahlensammlung.setMarktanteil(2);
        assertEquals(testKennzahlensammlung.getMarktanteil(), 2, 0.5);
    }

//    @Test
//    public void getLiquideMittel() throws Exception {
//        assertEquals(testKennzahlensammlung.getLiquideMittel(), 500000, 0.5);
//    }
//
//    @Test
//    public void setLiquideMittel() throws Exception {
//        testKennzahlensammlung.setLiquideMittel(2);
//        assertEquals(testKennzahlensammlung.getLiquideMittel(), 2, 0.5);
//
//    }

    @Test
    public void getBilanz() throws Exception {
assertNotNull(testKennzahlensammlung.getBilanz());
    }

    @Test
    public void getGuv() throws Exception {
        assertNotNull(testKennzahlensammlung.getGuv());
    }

    @Test
    public void setGuv() throws Exception {
testKennzahlensammlung.setGuv(null);
assertTrue(testKennzahlensammlung.getGuv() == null);
    }

    @After
    public void tearDown(){

    }

}
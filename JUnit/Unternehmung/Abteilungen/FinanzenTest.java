package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by D064018 on 21.01.2017.
 */
public class FinanzenTest{

    private Finanzen testfinanzen;
    private Kennzahlensammlung kennzahlensammlung;

    @Before
    public void setUp() throws Exception{
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        unternehmen.getAbteilung("hr").addMitarbeiter(1, 1000);
        testfinanzen = new Finanzen(kennzahlensammlung);
    }

    @Test
    public void kreditAufnehmen() throws Exception {
        testfinanzen.addMitarbeiter(1, 1000);
        testfinanzen.kreditAufnehmen(200000, 200);
        assertEquals(kennzahlensammlung.getBilanz().getFremdkapital(), 200000);
    }

    @After
    public void tearDown() throws Exception {

    }

}
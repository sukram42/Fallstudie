package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 21.01.2017.
 */
public class FinanzenTest extends TestCase {

    private Finanzen testfinanzen;
    private Kennzahlensammlung kennzahlensammlung;

    @Before
    public void createTestfinanzen(){
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testfinanzen = new Finanzen(kennzahlensammlung);
    }

    @Test
    public void kreditAufnehmen() throws Exception {
        testfinanzen.kreditAufnehmen(200000, 200);
        assertEquals(kennzahlensammlung.getBilanz().getFremdkapital(), 200000);
    }

    @Test
    public void update() throws Exception {
//ToDO

    }

}
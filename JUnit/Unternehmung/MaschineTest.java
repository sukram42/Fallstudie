package Unternehmung;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 11.01.2017.
 */

public class MaschineTest extends TestCase{


    private Maschine testMaschine;

    @Before
    public void testCreateMaschine() {
        testMaschine = new Maschine("Rucksack", 2);
        org.junit.Assert.assertNotNull(testMaschine);
    }

    @Test
    public void testGetKlasse(){
        org.junit.Assert.assertEquals( testMaschine.getKlasse(), 2);
    }

    @Test
    public void testGetKapazität(){
        org.junit.Assert.assertEquals( testMaschine.getKapazität(), 600);
    }

    @Test
    public void testGetAnschaffungskst(){
        org.junit.Assert.assertEquals( testMaschine.getAnschaffungskst(), 15000);
    }

    @Test
    public void reparieren() throws Exception {
        Unternehmen Unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        Kennzahlensammlung kennzahlensammlung = Unternehmen.getKennzahlensammlung();
       // testMaschine.setStatus(0.5);
       // testMaschine.reparieren(kenn);
    }

    @Test
    public void getStatus() throws Exception {
    assertEquals(testMaschine.getStatus(), 1);
    }

    @Test
    public void setStatus() throws Exception {
        testMaschine.setStatus(0.5);
        assertEquals(testMaschine.getStatus(), 0.5);
    }

    @Test
    public void getEnergiekosten() throws Exception {
assertEquals(testMaschine.getEnergiekosten(), 100);
    }

    public void tearDown(){

    }

}
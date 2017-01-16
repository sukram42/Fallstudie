package Unternehmung;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 11.01.2017.
 */

public class MaschineTest extends TestCase{

    private Maschine testMaschine;
    private String name = "bezeichnung";
    private int anzahl = 1;
    private int kapazität = 4;
    private int anschaffungskosten = 10000;

    public void setUp(){
        name = "bezeichnung";
        anzahl = 1;
        kapazität = 4;
        anschaffungskosten = 10000;
    }

    @Before
    public void testCreateMaschine() {
       // testMaschine = new Maschine( name, kapazität, anschaffungskosten, anzahl);
        org.junit.Assert.assertNotNull(testMaschine);
    }

    @Test
    public void testGetKlasse(){
        org.junit.Assert.assertEquals( testMaschine.getKlasse(), name);
    }

    @Test
    public void testGetKapazität(){
        org.junit.Assert.assertEquals( testMaschine.getKlasse(), kapazität);
    }

    @Test
    public void testGetAnschaffungskst(){
        org.junit.Assert.assertEquals( testMaschine.getKlasse(), anschaffungskosten);
    }

    @Test
    public void testGetAnzahl(){
      //  org.junit.Assert.assertEquals( testMaschine.getAnzahl(), anzahl);
    }

    @Test
    public void testSetAnzahl(){
        int neueAnzahl = 1;
       // testMaschine.setAnzahl(neueAnzahl);
       // org.junit.Assert.assertEquals( testMaschine.getAnzahl(), neueAnzahl);
    }

    public void tearDown(){

    }

}
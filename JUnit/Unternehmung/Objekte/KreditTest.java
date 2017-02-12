package Unternehmung.Objekte;

import Rules.Game;
import Unternehmung.Objekte.Kredit;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cs on 20.01.2017.
 */
public class KreditTest extends TestCase {
    int laufzeit;
    int hoehe;
    double restwert;
    double zinssatz;
    double zinsen;
    double tilgung;
    double annuitaet;
    long beginn;

    private Kredit testKredit;

    public void setUp() throws Exception {
        laufzeit= 4;
        hoehe = 100000;
        zinssatz = 0.05;
        restwert = hoehe;
        zinsen = zinssatz * restwert;
        tilgung = hoehe / laufzeit;
        annuitaet = tilgung + zinsen;
        beginn= Game.getTime();
    }

    @Before
    public void testcreateKredit(){
        testKredit = new Kredit(hoehe, laufzeit, zinssatz);
        assertNotNull(testKredit);
    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getLaufzeit() throws Exception {
        assertEquals(testKredit.getLaufzeit(),laufzeit);
    }

    @Test
    public void setLaufzeit() throws Exception {
        int neueLaufzeit = 2;
        testKredit.setLaufzeit(neueLaufzeit);
        assertEquals(testKredit.getLaufzeit(),neueLaufzeit);
    }

    @Test
    public void getHoehe() throws Exception {
        assertEquals(testKredit.getHoehe(),hoehe);
    }

    @Test
    public void setHoehe() throws Exception {
        int neueHoehe = 50000;
        testKredit.setHoehe(neueHoehe);
        assertEquals(testKredit.getHoehe(),neueHoehe);
    }

    @Test
    public void getRestwert() throws Exception {
        assertEquals(testKredit.getRestwert(), restwert);
    }

    @Test
    public void setRestwert() throws Exception {
        double neuerRestwert = 30000;
        testKredit.setRestwert(neuerRestwert);
        assertEquals(testKredit.getRestwert(),neuerRestwert);
    }

    @Test
    public void getZinssatz() throws Exception {
        assertEquals(testKredit.getZinssatz(),zinssatz);
    }

    @Test
    public void setZinssatz() throws Exception {
        double neuerZinssatz = 0.08;
        testKredit.setZinssatz(neuerZinssatz);
        assertEquals(testKredit.getZinssatz(),neuerZinssatz);
    }

    @Test
    public void getTilgung() throws Exception {
        assertEquals(testKredit.getTilgung(),tilgung);
    }

    @Test
    public void setTilgung() throws Exception {
        double neueTilgung = 5000;
        testKredit.setTilgung(neueTilgung);
        assertEquals(testKredit.getTilgung(),neueTilgung);

    }

    @Test
    public void getAnnuitaet() throws Exception {
        assertEquals(testKredit.getAnnuitaet(), annuitaet);
    }

    @Test
    public void setAnnuitaet() throws Exception {
        double neueAnnuitaet = 9000;
        testKredit.setAnnuitaet(neueAnnuitaet);
        assertEquals(testKredit.getAnnuitaet(),neueAnnuitaet);
    }

    @Test
    public void getZinsen() throws Exception {
        assertEquals(testKredit.getZinsen(),zinsen);
    }

    @Test
    public void getBeginn() throws Exception {
        assertEquals(testKredit.getBeginn(),beginn);
    }

}
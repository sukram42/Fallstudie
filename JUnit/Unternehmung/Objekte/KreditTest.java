package Unternehmung.Objekte;

import Rules.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by cs on 20.01.2017.
 */
public class KreditTest{
    int laufzeit;
    int hoehe;
    double restwert;
    double zinssatz;
    double zinsen;
    double tilgung;
    double annuitaet;
    Calendar beginn;

    private Kredit testKredit;

    @Before
    public void setUp() throws Exception {
        laufzeit= 4;
        hoehe = 100000;
        zinssatz = 0.05;
        restwert = hoehe;
        zinsen = zinssatz * restwert;
        tilgung = hoehe / laufzeit;
        annuitaet = tilgung + zinsen;
        beginn = Game.getCalendar();
        testKredit = new Kredit(hoehe, laufzeit, zinssatz);
        assertNotNull(testKredit);
    }


    @Test
    public void updateYearly() throws Exception {
testKredit.updateYearly();
assertEquals(testKredit.getRestwert(), 75000 , 0.1);
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
        assertEquals(testKredit.getRestwert(), restwert, 0.1);
    }

    @Test
    public void setRestwert() throws Exception {
        double neuerRestwert = 30000;
        testKredit.setRestwert(neuerRestwert);
        assertEquals(testKredit.getRestwert(),neuerRestwert, 0.1);
    }

    @Test
    public void getZinssatz() throws Exception {
        assertEquals(testKredit.getZinssatz(),zinssatz, 0.1);
    }

    @Test
    public void setZinssatz() throws Exception {
        double neuerZinssatz = 0.08;
        testKredit.setZinssatz(neuerZinssatz);
        assertEquals(testKredit.getZinssatz(),neuerZinssatz, 0.1);
    }

    @Test
    public void getTilgung() throws Exception {
        assertEquals(testKredit.getTilgung(),tilgung, 0.1 );
    }

    @Test
    public void setTilgung() throws Exception {
        double neueTilgung = 5000;
        testKredit.setTilgung(neueTilgung);
        assertEquals(testKredit.getTilgung(),neueTilgung, 0.1);

    }

    @Test
    public void getAnnuitaet() throws Exception {
        assertEquals(testKredit.getAnnuitaet(), annuitaet, 0.1);
    }

    @Test
    public void setAnnuitaet() throws Exception {
        double neueAnnuitaet = 9000;
        testKredit.setAnnuitaet(neueAnnuitaet);
        assertEquals(testKredit.getAnnuitaet(),neueAnnuitaet, 0.1);
    }

    @Test
    public void getZinsen() throws Exception {
        assertEquals(testKredit.getZinsen(),zinsen, 0.1);
    }

    @Test
    public void getBeginn() throws Exception {
        assertEquals(testKredit.getBeginn(), beginn);
    }

    @After
    public void tearDown() throws Exception {

    }

}
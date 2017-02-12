package Unternehmung.Objekte;

import Unternehmung.Objekte.SozialProjekt;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by D064018 on 16.01.2017.
 */
public class SozialProjektTest{

    private String name;
    private float impact;
    private float einmaligKosten;
    private float laufendeKosten;
    private float aktuelleKosten;
    private boolean active;

    SozialProjekt testSozialProjekt;

    @Before
    public void setUp(){
        name = "Kita";
        einmaligKosten = 5000;
        laufendeKosten = 1000;
        aktuelleKosten = 0;
        impact = 1;

        Unternehmen testUnternehmen = new Unternehmen( "Test Unternehmen", "12345", 600000);
         testSozialProjekt = new SozialProjekt(name, einmaligKosten, laufendeKosten, impact, testUnternehmen);
         assertNotNull(testSozialProjekt);
    }

    @Test
    public void start() throws Exception {
        testSozialProjekt.start();
        assertTrue(testSozialProjekt.isActive());
        assertEquals(testSozialProjekt.getAktKosten(), einmaligKosten, 0.5);
    }

    @Test
    public void stop() throws Exception {
testSozialProjekt.stop();
assertFalse(testSozialProjekt.isActive());
    }

    @Ignore
    public void update() throws Exception {
        assertEquals(testSozialProjekt.getAktKosten(), laufendeKosten, 0.5);
    }

    @Test
    public void resetAktKosten() throws Exception {
        testSozialProjekt.start();
        testSozialProjekt.resetAktKosten();
        assertEquals(testSozialProjekt.getAktKosten(), 0, 0.5);
    }

    @Test
    public void getImpact() throws Exception {
assertEquals(testSozialProjekt.getImpact(), impact, 0.5);
    }

    @Test
    public void getAktKosten() throws Exception {
assertEquals(testSozialProjekt.getAktKosten(), aktuelleKosten, 0.5);
    }

    @Test
    public void isActive() throws Exception {
        testSozialProjekt.start();
assertEquals(testSozialProjekt.isActive(), true);
    }

    @Test
    public void _getName() throws Exception {
assertEquals(testSozialProjekt.getName(), name);
    }

    @After
    public void tearDown() throws Exception {

    }

}
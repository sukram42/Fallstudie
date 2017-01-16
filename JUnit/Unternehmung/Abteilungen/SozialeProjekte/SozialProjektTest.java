package Unternehmung.Abteilungen.SozialeProjekte;

import Unternehmung.Unternehmen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 16.01.2017.
 */
public class SozialProjektTest extends TestCase{

    private String name;
    private float impact;
    private float einmaligKosten;
    private float laufendeKosten;
    private float aktuelleKosten;
    private boolean active;

    SozialProjekt testSozialProjekt;
    public void setUp(){
        name = "Kita";
        einmaligKosten = 5000;
        laufendeKosten = 1000;
        aktuelleKosten = 0;
        impact = 1;
    }

    @Before
    public void createSozialProjekt(){
        Unternehmen testUnternehmen = new Unternehmen( "Test Unternehmen", "12345", 600000);
         testSozialProjekt = new SozialProjekt(name, einmaligKosten, laufendeKosten, impact, testUnternehmen);
         assertNotNull(testSozialProjekt);
    }

    @Test
    public void start() throws Exception {
        testSozialProjekt.start();
        assertTrue(testSozialProjekt.isActive());
        assertEquals(testSozialProjekt.getAktKosten(), einmaligKosten);
    }

    @Test
    public void stop() throws Exception {
testSozialProjekt.stop();
assertFalse(testSozialProjekt.isActive());

    }

    @Test
    public void update() throws Exception {
        assertEquals(testSozialProjekt.getAktKosten(), laufendeKosten);
    }

    @Test
    public void resetAktKosten() throws Exception {
        testSozialProjekt.start();
        testSozialProjekt.resetAktKosten();
        assertEquals(testSozialProjekt.getAktKosten(), 0);
    }

    @Test
    public void getImpact() throws Exception {
assertEquals(testSozialProjekt.getImpact(), impact);
    }

    @Test
    public void getAktKosten() throws Exception {
assertEquals(testSozialProjekt.getAktKosten(), aktuelleKosten);
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

}
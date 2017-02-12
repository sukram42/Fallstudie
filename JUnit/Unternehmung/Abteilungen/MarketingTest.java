package Unternehmung.Abteilungen;

import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by D064018 on 12.02.2017.
 */
public class MarketingTest {

    private Kennzahlensammlung kennzahlensammlung;
    private Marketing testMarketing;
    private Unternehmen unternehmen;

    @Before
    public void setUp() throws ZuWenigMitarbeiterException{
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testMarketing = (Marketing)unternehmen.getAbteilung("marketing");
        HR hr = (HR)unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(1, 2000);
        testMarketing.addMitarbeiter(1, 2000);
        assertNotNull(testMarketing);
    }

    @Test
    public void marketingkampagneStarten() throws Exception {
testMarketing.marketingkampagneStarten("Social Media", 5);
assertEquals(testMarketing.getKampagnen().size(), 1);
    }

    @Test
    public void marketingkampagneAbbrechen() throws Exception {
        testMarketing.marketingkampagneStarten("Social Media", 5);
        testMarketing.marketingkampagneAbbrechen(0);
        assertTrue(testMarketing.getKampagnen().size() == 0);
    }

    @Test
    public void marktforschungStarten() throws Exception {
        testMarketing.marktforschungStarten(1);
        assertTrue(testMarketing.getMafos().size() == 1);
    }

    @Test
    public void marktforschungAbbrechen() throws Exception {
        testMarketing.marktforschungStarten(1);
        assertEquals(testMarketing.getMafos().size(), 1);
        testMarketing.marktforschungAbbrechen(1);
        assertEquals(testMarketing.getMafos().size(), 0);
    }

    @Test
    public void getKosten() throws Exception {
testMarketing.marktforschungStarten(1);
assertEquals(testMarketing.getKosten(), 50f, 0.2);
    }

    @Test
    public void getVerfuegbareMitarbeiter() throws Exception {
int startMitarbeiter;
startMitarbeiter = testMarketing.getVerfuegbareMitarbeiter();
testMarketing.marketingkampagneStarten("Social Media", 5);
testMarketing.marketingkampagneAbbrechen(0);
assertEquals(startMitarbeiter, testMarketing.getVerfuegbareMitarbeiter());
    }

    @Test
    public void getKampagnen() throws Exception {
assertEquals(testMarketing.getKampagnen().size(), 0);
    }

    @After
    public void tearDown() throws Exception {

    }

}
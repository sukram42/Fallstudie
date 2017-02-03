package Unternehmung;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 23.01.2017.
 */
public class MarketingkampagneTest {
    private String art;
    private int laufzeit;
    private Marketingkampagne testmarketingkampagne;


    @Before
    public void setUp() {
        art = "Print";
        laufzeit = 5;

        testmarketingkampagne = new Marketingkampagne(art, laufzeit);
        assertNotNull(testmarketingkampagne);
    }


    @Test
    public void getArt() throws Exception {
        assertEquals(testmarketingkampagne.getArt(), art);
    }

    @Test
    public void getImpact() throws Exception {
        assertEquals(testmarketingkampagne.getImpact(), 0.002f, 0.5);
    }

    @Test
    public void getKosten() throws Exception {
        assertEquals(testmarketingkampagne.getKosten(), 10000, 0.5);
    }

    @Test
    public void getNoetigeMitarbeiter() throws Exception {
        assertEquals(testmarketingkampagne.getNoetigeMitarbeiter(), 3);
    }

    @Test
    public void getBeginn() throws Exception {
        assertNotNull(testmarketingkampagne.getBeginn());
    }

    @Test
    public void getEnd() throws Exception {
        assertNotNull(testmarketingkampagne.getEnd());
    }

    @After
    public void tearDown() {

    }
}
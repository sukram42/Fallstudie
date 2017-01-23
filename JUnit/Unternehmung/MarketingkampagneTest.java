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
public class MarketingkampagneTest{
    private String art;
    private int laufzeit;
    private Marketingkampagne testmarketingkampagne;


    @Before
    public void setUp(){
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
    public void setArt() throws Exception {
        testmarketingkampagne.setArt("Social Media");
assertEquals(testmarketingkampagne.getArt(), "Social Media");
    }

    @Test
    public void getImpact() throws Exception {
assertEquals(testmarketingkampagne.getImpact(), 0.002f, 0.5);
    }

    @Test
    public void setImpact() throws Exception {
testmarketingkampagne.setImpact(0.0025f);
        assertEquals(testmarketingkampagne.getImpact(), 0.0025f, 0.5);
    }

    @Test
    public void getKosten() throws Exception {
assertEquals(testmarketingkampagne.getKosten(), 50, 0.5);
    }

    @Test
    public void setKosten() throws Exception {
testmarketingkampagne.setKosten(100);
        assertEquals(testmarketingkampagne.getKosten(), 100, 0.5);

    }

    @Test
    public void getNoetigeMitarbeiter() throws Exception {
assertEquals(testmarketingkampagne.getNoetigeMitarbeiter(), 2);
    }

    @Test
    public void getBeginn() throws Exception {
assertNotNull(testmarketingkampagne.getBeginn());
    }

    @Test
    public void getEnd() throws Exception {
        assertNotNull(testmarketingkampagne.getEnd());
    }

    @Test
    public void setEnd() throws Exception {
        Calendar neuesDatum = new GregorianCalendar(2019, 4, 20, 14, 29, 21);
        testmarketingkampagne.setEnd(neuesDatum);
       assertEquals(testmarketingkampagne.getEnd(), neuesDatum);
    }

    @After
    public void tearDown(){

    }
}
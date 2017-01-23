package Unternehmung;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cs on 17.01.2017.
 */
public class ProduktTest extends TestCase {
    private String name;
    private char qualitaetsstufe;
    private double herstellkosten;
    private double forschungsbonus;

    private Produkt testProdukt;

    public void setUp(){
        name = "Rucksack";
        qualitaetsstufe = 'A';
        forschungsbonus = 0.1;
        herstellkosten = testfindherstellkosten( name, qualitaetsstufe)* forschungsbonus;
    }

    @Before
    public void testcreateProdukt(){
        testProdukt = new Produkt(name, qualitaetsstufe, forschungsbonus);
        assertNotNull(testProdukt);
    }



    @After
    public void tearDown() throws Exception {

    }

    @Test
    private void testfindherstellkosten(){
        double result = testProdukt.findHerstellkosten( testProdukt.getName(), testProdukt.getQualitaetsstufe() );
        assertEquals( testProdukt, result );

    }

    @Test
    public void getName() throws Exception {
        assertEquals( testProdukt.getName(), name);
    }

    @Test
    public void setName() throws Exception {
        String neuerName = "Duffel";
        testProdukt.setName(neuerName);
        assertEquals(testProdukt.getName(), neuerName);

    }

    @Test
    public void getQualitaetsstufe() throws Exception {
        assertEquals( testProdukt.getQualitaetsstufe(), qualitaetsstufe);
    }

    @Test
    public void setQualitaetsstufe() throws Exception {
        char neueQualitaetsstufe = 'B';
        testProdukt.setQualitaetsstufe(neueQualitaetsstufe);
        assertEquals( testProdukt.getQualitaetsstufe(), neueQualitaetsstufe);

    }

    @Test
    public void getHerstellkosten() throws Exception {
        assertEquals( testProdukt.getHerstellkosten(), herstellkosten);
    }

    @Test
    public void setHerstellkosten() throws Exception {
        double neueHerstellkosten = 20;
        testProdukt.setHerstellkosten(neueHerstellkosten);
        assertEquals( testProdukt.getHerstellkosten(), neueHerstellkosten);

    }

}
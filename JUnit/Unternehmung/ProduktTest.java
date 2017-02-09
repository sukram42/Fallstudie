package Unternehmung;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by cs on 17.01.2017.
 */
public class ProduktTest{


    private String name;
    private char qualitaetsstufe;
    private double herstellkosten;
    private double forschungsbonus;

    private Produkt testProdukt;

    @Before
    public void setUp(){
        name = "Rucksack";
        qualitaetsstufe = 'A';
        testProdukt = new Produkt(name, qualitaetsstufe, 1);
        assertNotNull(testProdukt);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getForschungsbonus() throws Exception {
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "af1r2", 100000);
        assertEquals(testProdukt.getForschungsbonus(), 1, 0.5);
    }

    @Test
    public void setForschungsbonus() throws Exception {
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "af1r2", 100000);
        testProdukt.setForschungsbonus(0.9);
        assertEquals(testProdukt.getForschungsbonus(), 0.9, 0.5);
    }


    @Test
    public void getName_() throws Exception {
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
        assertEquals( testProdukt.getHerstellkosten(), 70, 0.5);
    }

    @Test
    public void setHerstellkosten() throws Exception {
        double neueHerstellkosten = 20;
        testProdukt.setHerstellkosten(neueHerstellkosten);
        assertEquals( testProdukt.getHerstellkosten(), neueHerstellkosten, 0.5);

    }
}
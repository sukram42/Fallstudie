package Unternehmung;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cs on 20.01.2017.
 */
public class ProduktlinieTest {
    private String id;
    private Produkt produkt;
    private int menge;
    private int laufzeit;

    private  Produktlinie testProduktlinie;


    public void setUp() throws Exception {
        id = "Linie594";
        produkt = new Produkt("Duffel",'B',0.2);
        menge = 5000;
        laufzeit = 2;
    }

    @Before
    public void testcreateProduktlinie(){
        testProduktlinie = new Produktlinie(produkt, menge, laufzeit);
        assertNotNull(testProduktlinie);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals( testProduktlinie.getId(), id);
    }

    @Test
    public void setId() throws Exception {
        String neueId= "Linie555";
        testProduktlinie.setId(neueId);
        assertEquals(testProduktlinie.getId(), neueId);

    }

    @Test
    public void getProdukt() throws Exception {
        assertEquals(testProduktlinie.getProdukt(), produkt);
    }

    @Test
    public void setProdukt() throws Exception {
        Produkt neuesProdukt = new Produkt("Rucksack", 'C', 0.1);
        testProduktlinie.setProdukt(neuesProdukt);
        assertEquals(testProduktlinie.getProdukt(),neuesProdukt);
    }

    @Test
    public void getMenge() throws Exception {
        assertEquals(testProduktlinie.getMenge(),menge);
    }

    @Test
    public void setMenge() throws Exception {
        int neueMenge = 2000;
        testProduktlinie.setMenge(neueMenge);
        assertEquals(testProduktlinie.getMenge(),neueMenge);
    }

    @Test
    public void getLaufzeit() throws Exception {
        assertEquals(testProduktlinie.getLaufzeit(),laufzeit);
    }

    @Test
    public void setLaufzeit() throws Exception {
        int neueLaufzeit = 1;
        testProduktlinie.setLaufzeit(neueLaufzeit);
        assertEquals(testProduktlinie.getLaufzeit(),neueLaufzeit);
    }

}
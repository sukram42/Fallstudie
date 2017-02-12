package Unternehmung.Objekte;

import Rules.Game;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by cs on 20.01.2017.
 */
public class VertragTest{



    private Produktlinie produktlinie;
    private float preis;
    private float strafe;
    private String kunde;
    private int laufzeit;
    private Calendar beginn;
    private Calendar end;

    private Vertrag testVertrag;

    @Before
    public void setUp() throws Exception {
        produktlinie = new Produktlinie(new Produkt("Duffel", 'B', 0.1), 10000, 1);
        preis = 100000;
        strafe = preis * 0.75f;
        kunde = "Kunde AG";
        laufzeit = 1;
        beginn = Game.getCalendar();
        end = beginn;
        end.add(Calendar.MONTH, laufzeit);
        testVertrag = new Vertrag(produktlinie, kunde, laufzeit);
        assertNotNull(testVertrag);
    }

    @Test
    public void setPreisByKennzahlen() throws Exception {
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        Kennzahlensammlung kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testVertrag.setPreisByKennzahlen(kennzahlensammlung);
       // testVertrag
    }

    @Test
    public void getProduktlinie() throws Exception {
        assertEquals(testVertrag.getProduktlinie(), produktlinie);
    }

    @Test
    public void setProduktlinie() throws Exception {
        Produktlinie neueProduktlinie = new Produktlinie(new Produkt("Rucksack", 'A', 0.25), 20000, 2);
        testVertrag.setProduktlinie(neueProduktlinie);
        assertEquals(testVertrag.getProduktlinie(), neueProduktlinie);
    }

    @Test
    public void getPreis() throws Exception {
        assertEquals(testVertrag.getPreis(), 0, 0.1);
    }

    @Test
    public void setPreis() throws Exception {
        float neuerPreis = 120000;
        testVertrag.setPreis(neuerPreis);
        assertEquals(testVertrag.getPreis(), neuerPreis, 0.1);
    }

    @Test
    public void getStrafe() throws Exception {
        assertEquals(testVertrag.getStrafe(), 0, 0.1);
    }

    @Test
    public void setStrafe() throws Exception {
        float neueStrafe = 60000;
        testVertrag.setStrafe(neueStrafe);
        assertEquals(testVertrag.getStrafe(), neueStrafe, 0.1);
    }

    @Test
    public void getKunde() throws Exception {
        assertEquals(testVertrag.getKunde(), kunde);
    }

    @Test
    public void setKunde() throws Exception {
        String neuerKunde = "Kunde GmbH";
        testVertrag.setKunde(neuerKunde);
        assertEquals(testVertrag.getKunde(), neuerKunde);
    }

    @Test
    public void getLaufzeit() throws Exception {
        assertEquals(testVertrag.getLaufzeit(), laufzeit);
    }

    @Test
    public void setLaufzeit() throws Exception {
        int neueLaufzeit = 2;
        testVertrag.setLaufzeit(neueLaufzeit);
        assertEquals(testVertrag.getLaufzeit(), neueLaufzeit);
    }

    @Test
    public void getBeginn() throws Exception {
        assertEquals(testVertrag.getBeginn(), beginn);
    }

    @Test
    public void getEnd() throws Exception {
        assertNotNull(testVertrag.getEnd());
    }

    @Test
    public void setEnd() throws Exception {
        Calendar neuesEnde = testVertrag.getEnd();
        neuesEnde.add(Calendar.MONTH, 2);
        testVertrag.setEnd(neuesEnde);
        assertEquals(testVertrag.getEnd(), neuesEnde);
    }

    @After
    public void tearDown() throws Exception {

    }


}
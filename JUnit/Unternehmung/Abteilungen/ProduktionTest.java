package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 11.01.2017.
 */
public class ProduktionTest extends TestCase{

    private Kennzahlensammlung kennzahlensammlung;
    private Produktion testProduktion;

    @Before
    public void testCreateProduktion() {
        Unternehmen testUnternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = testUnternehmen.getKennzahlensammlung();
        testProduktion = new Produktion(kennzahlensammlung);
        assertNotNull(testProduktion);
    }

    @Test
    public void produzieren() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000);
        assertEquals(testProduktion.getMaxProdMenge(), (300 - 100));
    }

    @Test
    public void maschinenKaufen() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
        testProduktion.addMitarbeiter(2, 10000); //Wird das Gehalt mit Erstellen der Mitarbeiter schon den liquiden Kosten abgezogen? hier nicht!
        assertEquals(kennzahlensammlung.getLiquideMittel(), 500000 - 65000 - 7500);
        assertEquals(testProduktion.getMaxProdMenge(), 300); //Kapazität der einzelnen Maschine
    }

    @Test
    public void produktionshalleKaufen() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        assertEquals(testProduktion.getFreienMaschinenPlatz(), 50);
        assertEquals(kennzahlensammlung.getLiquideMittel(), (500000 - 65000));
    }

    @Test
    public void lagerhalleKaufen() throws Exception {
        testProduktion.lagerhalleKaufen(2);
        assertEquals(testProduktion.getFreienLagerPlatz(), 5000);
        assertEquals(kennzahlensammlung.getLiquideMittel(), (500000 - 20000));
    }

    @Test
    public void update() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000); //Herstellungskosten sind 20
        testProduktion.update();
        assertEquals(kennzahlensammlung.getLiquideMittel(), 100 + 100 * 20 );
    }

    @Test
    public void getForschungsbonusById() throws Exception {

    }

    @Test
    public void getTaeglicheEnergiekosten() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
        assertEquals(testProduktion.getTaeglicheEnergiekosten(), 100);
    }

    @Test
    public void getTaeglicheHerstellkosten() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000); //Herstellungskosten sind 20
        assertEquals(testProduktion.getTaeglicheHerstellkosten(), 20 * 100);
    }

    @Test
    public void getMaxProdMenge() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen(1, 1);
    }

    @Test
    public void getFreienLagerPlatz() throws Exception {
        testProduktion.lagerhalleKaufen(2);
        assertEquals(testProduktion.getFreienLagerPlatz(), 5000);
    }

    @Test
    public void getFreienMaschinenPlatz() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        assertEquals(testProduktion.getFreienMaschinenPlatz(), 50);
    }

    @Test
    public void getProduktlinien() throws Exception {

    }
}
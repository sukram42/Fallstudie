package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by D064018 on 11.01.2017.
 */
public class ProduktionTest{

    private Kennzahlensammlung kennzahlensammlung;
    private Produktion testProduktion;

    @Before
    public void setUp() {
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testProduktion = (Produktion)unternehmen.getAbteilung("produktion");
        assertNotNull(testProduktion);
    }

    @Test
    public void getGesamtenLagerPlatz() throws Exception {
        testProduktion.lagerhalleKaufen(2);
        testProduktion.lagerhalleKaufen(2);
assertEquals(testProduktion.getGesamtenLagerPlatz(), 2 * 5000);
    }

    @Test
    public void getGesamtenProduktionshallenPlatz() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.produktionshalleKaufen(2);
        assertEquals(testProduktion.getGesamtenProduktionshallenPlatz(), 50 * 2);
    }

    @Test
    public void setForschungsbonus() throws Exception {
testProduktion.setForschungsbonus("ReisetascheA", 0.9);
assertEquals(testProduktion.getForschungsbonusById("ReisetascheA"), 0.9, 0.5);
    }

    @Test
    public void getForschungsboni() throws Exception {
assertFalse(testProduktion.getForschungsboni().isEmpty());
    }

    @Test
    public void getLager() throws Exception {
        assertNotNull(testProduktion.getLager());
    }

    @Test
    public void getAufträge() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000);
        assertFalse(testProduktion.getAufträge().isEmpty());
    }

    @Test
    public void getMaschinen() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        assertFalse(testProduktion.getMaschinen().isEmpty());
    }



    @Test
    public void produzieren() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000);
        assertEquals(testProduktion.getMaxProdMenge("Rucksack"), (300 - 100));
    }

    @Test
    public void maschinenKaufen() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        testProduktion.addMitarbeiter(2, 10000); //Wird das Gehalt mit Erstellen der Mitarbeiter schon den liquiden Kosten abgezogen? hier nicht!
        assertEquals(kennzahlensammlung.getBilanz().getLiquideMittel(), 500000 - 65000 - 7500, 0.5);
        assertEquals(testProduktion.getMaxProdMenge("Rucksack"), 300); //Kapazität der einzelnen Maschine
    }

    @Test
    public void produktionshalleKaufen() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        assertEquals(testProduktion.getFreienProduktionshallenPlatz(), 50);
        assertEquals(kennzahlensammlung.getBilanz().getLiquideMittel(), (500000 - 65000), 0.5);
    }

    @Test
    public void lagerhalleKaufen() throws Exception {
        testProduktion.lagerhalleKaufen(2);
        assertEquals(testProduktion.getFreienLagerPlatz(), 5000);
        assertEquals(kennzahlensammlung.getBilanz().getLiquideMittel(), (500000 - 20000), 0.5);
    }

    @Ignore
    public void update() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000); //Herstellungskosten sind 20
        testProduktion.update();
        assertEquals(kennzahlensammlung.getBilanz().getLiquideMittel(), 100 + 100 * 20, 0.5);
    }

    @Ignore
    public void getForschungsbonusById() throws Exception {

    }

    @Test
    public void getTaeglicheEnergiekosten() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        assertEquals(testProduktion.getTaeglicheEnergiekosten(), 100, 0.5);
    }

    @Test
    public void getTaeglicheHerstellkosten() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
        testProduktion.addMitarbeiter(2, 10000);
        testProduktion.produzieren("Rucksack", 'A', 100, 50000); //Herstellungskosten sind 20
        assertEquals(testProduktion.getTaeglicheHerstellkosten(), 20 * 100, 0.5);
    }

    @Test
    public void getMaxProdMenge() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        testProduktion.maschinenKaufen("Rucksack",1, 1);
    }

    @Test
    public void getFreienLagerPlatz() throws Exception {
        testProduktion.lagerhalleKaufen(2);
        assertEquals(testProduktion.getFreienLagerPlatz(), 5000);
    }

    @Test
    public void getFreienProduktionshallenPlatz() throws Exception {
        testProduktion.produktionshalleKaufen(2);
        assertEquals(testProduktion.getFreienProduktionshallenPlatz(), 50);
    }

    @After
    public void tearDown() throws Exception {

    }
}
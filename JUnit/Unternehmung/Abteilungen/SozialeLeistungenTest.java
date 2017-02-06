package Unternehmung.Abteilungen;

import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 16.01.2017.
 */
public class SozialeLeistungenTest {

    private HR testSozialeLeistungen;

    @Before
    public void setUp() {
        Unternehmen unternehmen = new Unternehmen("Test Unternehmen", "12345", 600000);
        testSozialeLeistungen = new HR(unternehmen, unternehmen.getKennzahlensammlung());
        assertNotNull(testSozialeLeistungen);
        testSozialeLeistungen.initProjects();
    }

    @Test
    public void initProjects() throws Exception {
        assertNotNull(testSozialeLeistungen.getProjects().get(1));
    }

    @Test
    public void update() throws Exception {
        //     testSozialeLeistungen.startProjekt("wifi");

    }

    @Test
    public void startProjekt() throws Exception {
        testSozialeLeistungen.getProjects().get(1).start();
        assertTrue(testSozialeLeistungen.getProjects().get(1).isActive());
    }

    @Test
    public void stopProjekt() throws Exception {
        testSozialeLeistungen.getProjects().get(1).start();         //Zuerst ein Projekt starten
        assertTrue(testSozialeLeistungen.getProjects().get(1).isActive());
        testSozialeLeistungen.getProjects().get(1).stop();      //Dann das Projekt stoppen
        assertFalse(testSozialeLeistungen.getProjects().get(1).isActive());
    }

    @Test
    public void getProjects() throws Exception {
        assertFalse(testSozialeLeistungen.getProjects().get(1).isActive());
    }

    @After
    public void tearDown() throws Exception {

    }
}
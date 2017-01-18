package Unternehmung.Abteilungen;

import Unternehmung.Unternehmen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 16.01.2017.
 */
public class SozialeLeistungenTest extends TestCase{

    private SozialeLeistungen testSozialeLeistungen;

    @Before
    public void createSozialeLeistungen(){
        Unternehmen unternehmen = new Unternehmen( "Test Unternehmen", "12345", 600000);
        testSozialeLeistungen = new SozialeLeistungen(unternehmen);
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

}
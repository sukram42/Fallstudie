package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by D064018 on 21.01.2017.
 */
public class ForschungTest{

    private Forschung testForschung;
    private Unternehmen unternehmen;
    private Kennzahlensammlung kennzahlensammlung;
    private Produktion produktion;
    String forschungsobjekt;

    @Before
    public void setUp(){
        forschungsobjekt = "ReisetascheA";
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        produktion = (Produktion)unternehmen.getAbteilung("produktion");
        testForschung = new Forschung(kennzahlensammlung, produktion);
        assertNotNull(testForschung);
    }

    @Test
    public void setImagebonus() throws Exception {
testForschung.setImagebonus(forschungsobjekt, 0.05);
assertEquals(testForschung.getImagebonusById(forschungsobjekt), 0.05, 0.5);
    }

    @Test
    public void getImagebonusById() throws Exception {
assertEquals(testForschung.getImagebonusById(forschungsobjekt), 0, 0.5);
    }

    @Test
    public void starteProjekt() throws Exception {
        testForschung.addMitarbeiter(5, 8000);
        testForschung.starteProjekt(produktion, testForschung, forschungsobjekt, 5, 100, true, kennzahlensammlung);
        assertFalse(testForschung.getProjekte().isEmpty());
    }

    @Test
    public void getProjekte() throws Exception {
        testForschung.addMitarbeiter(5, 8000);
        testForschung.starteProjekt(produktion, testForschung, forschungsobjekt, 5, 100, true, kennzahlensammlung);
        assertFalse(testForschung.getProjekte().isEmpty());
    }

    @Test
    public void forschungsprojektAbbrechen() throws Exception {
        //double erwarteterBonus = x * 0.7;
        testForschung.addMitarbeiter(5, 8000);
        testForschung.starteProjekt(produktion, testForschung, forschungsobjekt, 5, 100, true, kennzahlensammlung);
        //assertEquals(produktion.getForschungsbonusById(forschungsobjekt), erwarteterBonues);
    }

    @Test
    public void forschungsprojektAbschließen() throws Exception {
        //double erwarteterBonus = x;
        testForschung.addMitarbeiter(5, 8000);
        testForschung.starteProjekt(produktion, testForschung, forschungsobjekt, 5, 100, true, kennzahlensammlung);
       // assertEquals(produktion.getForschungsbonusById(forschungsobjekt), erwateterBonues);
    }

    @Test
    public void getVerfügbareProdukte() throws Exception {
assertTrue(testForschung.getVerfügbareProdukte().size() == 12);
    }

    @After
    public void tearDown() throws Exception {

    }

}
package Unternehmung.Abteilungen;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by D064018 on 23.01.2017.
 */
public class VertriebTest{

    private Game game;
    private Unternehmen unternehmen;
    private Vertrieb testVertrieb;

    @Before
    public void setUp(){
        game = new Game();
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        testVertrieb = (Vertrieb)unternehmen.getAbteilung("vertrieb");
        game.getCompanies().add(unternehmen);
    }

    @Test
    public void bewerben() throws Exception {
        HR hr = (HR)unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(1, 1000);
        testVertrieb.addMitarbeiter(1, 2000);
        testVertrieb.bewerben(1);
        assertTrue(testVertrieb.getOpportunities().size() == 1);
    }

    @Test(expected = ZuWenigMitarbeiterException.class)
    public void bewerbenFail() throws ZuWenigMitarbeiterException{
        testVertrieb.bewerben(1);
    }

    @Test
    public void zuschlagBekommen() throws Exception {
        HR hr = (HR)unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(1, 1000);
        testVertrieb.addMitarbeiter(1, 2000);
        testVertrieb.bewerben(1);
        testVertrieb.zuschlagBekommen(1);
        assertTrue(testVertrieb.getAccounts().size() > 0);
    }


    @Test
    public void getOpportunities() throws Exception {
        HR hr = (HR)unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(1, 1000);
        testVertrieb.addMitarbeiter(1, 2000);
        testVertrieb.bewerben(1);
        assertTrue(testVertrieb.getOpportunities().size() > 0);
    }

    @Test
    public void getAccounts() throws Exception {
        HR hr = (HR)unternehmen.getAbteilung("hr");
        hr.addMitarbeiter(1, 1000);
        testVertrieb.addMitarbeiter(1, 2000);
        testVertrieb.bewerben(1);
        testVertrieb.zuschlagBekommen(1);
        assertTrue(testVertrieb.getAccounts().size() == 1);
    }

    @After
    public void tearUp(){

    }
}
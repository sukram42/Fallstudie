package Unternehmung.Abteilungen;

import Exceptions.BereitsBeworbenException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by D064018 on 23.01.2017.
 */
public class VertriebTest{

    private static Game game;
    private Unternehmen unternehmen;
    private Vertrieb testVertrieb;

    @BeforeClass
    public static void createGameVertrieb(){
        game = new Game();
    }

    @Before
    public void setUp(){
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        testVertrieb = (Vertrieb)unternehmen.getAbteilung("vertrieb");
        game.getCompanies().add(unternehmen);
    }

    @Test
    public void getFreieMitarbeiter() throws Exception {
        unternehmen.getAbteilung("vertrieb").addMitarbeiter(1, 2000);
        assertEquals(testVertrieb.getFreieMitarbeiter(), 1);
    }

    @Test
    public void getAccountsAsMap() throws Exception {
        testVertrieb.addMitarbeiter(2, 2000);
        testVertrieb.bewerben(Game.getAusschreibungen().get(1));
        testVertrieb.getAccounts().add(Game.getAusschreibungen().get(1).getVertrag());
        assertEquals(testVertrieb.getAccountsAsMap().size() , 2);

    }

    @Test
    public void getVerkaufteProdukte() throws Exception {
        assertEquals(testVertrieb.getVerkaufteProdukte(), 0);
    }


    @Test
    public void bewerben() throws Exception {
        testVertrieb.addMitarbeiter(2, 2000);
        testVertrieb.bewerben(Game.getAusschreibungen().get(1));
        assertTrue(testVertrieb.getOpportunities().size() == 1);
    }

    @Test(expected = ZuWenigMitarbeiterException.class)
    public void bewerbenFail() throws ZuWenigMitarbeiterException{
        try {
            testVertrieb.bewerben(Game.getAusschreibungen().get(1));
        } catch (BereitsBeworbenException e){
            e.printStackTrace();
        }
    }


    @Test
    public void getOpportunities() throws Exception {
        testVertrieb.addMitarbeiter(2, 2000);
        testVertrieb.bewerben(Game.getAusschreibungen().get(1));
        assertTrue(testVertrieb.getOpportunities().size() > 0);
    }

    @Test
    public void getAccounts() throws Exception {
        testVertrieb.addMitarbeiter(2, 2000);
        testVertrieb.bewerben(Game.getAusschreibungen().get(1));
        testVertrieb.getAccounts().add(Game.getAusschreibungen().get(1).getVertrag());
        assertTrue(testVertrieb.getAccounts().size() == 2);
    }

    @After
    public void tearUp(){
        game.getCompanies().remove(unternehmen);
    }
}
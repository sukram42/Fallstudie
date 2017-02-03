package Rules;

import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 27.01.2017.
 */
public class GameTest{

    private Game testGame;
    private Kennzahlensammlung kennzahlensammlung;
    private Produktion produktion;
    private Unternehmen unternehmen;
    private String unternehmensname;

    @Before
    public void setUp() throws Exception{
        unternehmensname = "Test_Unternehmen";
        testGame = new Game();
        unternehmen = new Unternehmen(unternehmensname, "12345", 500000);
        testGame.getCompanies().add(unternehmen);
        assertNotNull(testGame);
    }

    @Test
    public void getCompanies() throws Exception {
        assertTrue(Game.getCompanies().contains(unternehmen));
    }

    @Test
    public void getUnternehmenByName() throws Exception {
        assertNotNull(Game.getUnternehmenByName(unternehmensname));
    }

    @Test
    public void getTime() throws Exception {
assertNotNull(Game.getTime());
    }

    @Test
    public void getTimeString() throws Exception {
assertNotNull(Game.getTimeString());
    }

    @Test
    public void getCalendar() throws Exception {
        assertNotNull(Game.getCalendar());
    }

    @Test
    public void getAusschreibungen() throws Exception {
        assertTrue(Game.getAusschreibungen().size() == 0);
    }

    @Test
    public void _run() throws Exception {
        testGame.run();
        assertNotNull(Game.getAusschreibungen());
    }

    @Test
    public void updateCounter() throws Exception {
    testGame.updateCounter();
        Calendar calendar = new GregorianCalendar(2010, 1, 2);
        assertEquals(Game.getCalendar(), calendar); //angeblich sei calender == 2012, 2, 3
    }

    @After
    public void tearDown(){
        System.setOut(null);
        System.setErr(null);
    }

}
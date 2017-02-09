package Rules;

import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Kennzahlen.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 27.01.2017.
 */

public class GameTest {

    private static Game testGame;
    private Kennzahlensammlung kennzahlensammlung;
    private Produktion produktion;
    private Unternehmen unternehmen;
    private String unternehmensname;

    @BeforeClass
    public static void createGame() {
        testGame = new Game();
    }

    @Before
    public void setUp() throws Exception {
        unternehmensname = "Test_Unternehmen";
        unternehmen = new Unternehmen(unternehmensname, "12345", 500000);
        testGame.getCompanies().add(unternehmen);
        assertNotNull(unternehmen);
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
        testGame.run();
        assertTrue(Game.getAusschreibungen().size() > 0);
    }

    @Test
    public void _run() throws Exception {
        long a = Game.getTime();
        testGame.run();
        assertFalse(Game.getTime() == a);
    }

    @After
    public void tearDown() {
        testGame.getCompanies().remove(unternehmen);
    }

}

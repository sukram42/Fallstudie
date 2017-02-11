package com.fallstudie.Interface;

import Rules.Game;
import Unternehmung.Unternehmen;
import controller.GameController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by D064018 on 06.02.2017.
 */
public class GameInterfaceTest {

    //  private static GameInterface testGameInterface;
    private GameController testGameInterface;

   /* @BeforeClass
    public static void createGameInterface() {
        testGameInterface = new GameInterface();
    }*/


    @Before
    public void setUp() throws Exception {
        testGameInterface = new GameController();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTimer() throws Exception {
        assertEquals(testGameInterface.getTimer().getEntity().toString(), "01 Jan 2010");
    }

    @Test
    public void authenticateUser() throws Exception {
        Unternehmen testUnternehmen = new Unternehmen("Test_Unternehmen", "12345", 100000);
        Game.getCompanies().add(testUnternehmen);
        assertEquals(testGameInterface.authenticateUser("Test_Unternehmen", "12345").getStatus(), 200);
    }

    @Test
    public void validate() throws Exception {
        //Wie ist ein korrekter autheader
        //  Header h = new Header( );
        // assertEquals(testGameInterface.validate("blo").getStatus(), );
    }

    @Test
    public void validateToken() throws Exception {

    }

    @Test
    public void logOut() throws Exception {
        //  SecurityContext.CLIENT_CERT_AUTH
    }

    @Test
    public void contextInitialized() throws Exception {

    }

    @Test
    public void contextDestroyed() throws Exception {

    }

}
package Unternehmung.Abteilungen;

import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 23.01.2017.
 */
public class VertriebTest {

    private Kennzahlensammlung kennzahlensammlung;
    private Produktion produktion;

    @Before
    public void setUp(){
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        produktion = (Produktion)unternehmen.getAbteilung("produktion");
    }

    @Test
    public void bewerben() throws Exception {

    }

    @Test
    public void vertragVerlängern() throws Exception {

    }

    @Test
    public void zuschlagBekommen() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getOpportunities() throws Exception {

    }

    @Test
    public void getAccounts() throws Exception {

    }

}
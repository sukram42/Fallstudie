package Unternehmung.Objekte;

import Unternehmung.Objekte.Ausschreibung;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 23.01.2017.
 */
public class AusschreibungTest{

    private Ausschreibung testausschreibung;

    @Before
    public void setUp(){
        testausschreibung = new Ausschreibung();
        assertNotNull(testausschreibung);
    }

    @Test
    public void getVertrag() throws Exception {
assertNotNull(testausschreibung);
    }

    @After
    public void tearDown(){

    }
}
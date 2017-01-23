package Unternehmung;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 20.01.2017.
 */
public class HalleTest{

    private Halle testhalle;
    private int größe;
    private String halle;

    @Before
    public void setUp(){
        halle = "Lagerhalle"; //Kap 5000, Preis 20000
        größe = 2;
        testhalle = new Halle(halle, größe);
        assertNotNull(testhalle);
    }

    @Test
    public void getGröße() throws Exception {
assertEquals(testhalle.getGröße(), größe);
    }

    @Test
    public void getKapazität() throws Exception {
        assertEquals(testhalle.getKapazität(), 5000);
    }

    @Test
    public void getPreis() throws Exception {
        assertEquals(testhalle.getPreis(), 20000, 0.5);
    }

    @After
    public void tearDown() throws Exception {

    }

}
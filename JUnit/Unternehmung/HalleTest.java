package Unternehmung;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 20.01.2017.
 */
public class HalleTest extends TestCase{

    private Halle testhalle;
    private int größe;
    private String halle;

    @Before
    public void createTesthalle(){
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
        assertEquals(testhalle.getPreis(), 20000);
    }


}
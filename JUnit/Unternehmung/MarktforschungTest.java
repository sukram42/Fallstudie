package Unternehmung;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 23.01.2017.
 */
public class MarktforschungTest{

    private Marktforschung testMarktforschung;

    @Before
    public void setUp(){
        testMarktforschung = new Marktforschung(2);
    }

    @Test
    public void getUmfang() throws Exception {
assertEquals(testMarktforschung.getUmfang(), 2);
    }

    @Test
    public void setUmfang() throws Exception {
        testMarktforschung.setUmfang(1);
        assertEquals(testMarktforschung.getUmfang(), 1);
    }

    @Test
    public void getKosten() throws Exception {
assertEquals(testMarktforschung.getKosten(), 50, 0.5);
    }

    @Test
    public void setKosten() throws Exception {
testMarktforschung.setKosten(80);
        assertEquals(testMarktforschung.getKosten(), 80, 0.5);
    }

    @Test
    public void getImpact() throws Exception {
assertEquals(testMarktforschung.getImpact(), 0.12f, 0.5);
    }

    @Test
    public void setImpact() throws Exception {
testMarktforschung.setImpact(2);
        assertEquals(testMarktforschung.getImpact(), 2, 0.5);
    }

    @Test
    public void getNoetigeMitarbeiter() throws Exception {
assertEquals(testMarktforschung.getNoetigeMitarbeiter(), 3);
    }

    @Test
    public void getBeginn() throws Exception {
assertNotNull(testMarktforschung.getBeginn());
    }

    @Test
    public void getEnd() throws Exception {
assertNotNull(testMarktforschung.getEnd());
    }

}
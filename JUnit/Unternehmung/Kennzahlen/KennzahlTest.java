package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by D064018 on 23.01.2017.
 */
public class KennzahlTest{

    private Unternehmen unternehmen;
    private Kennzahl testkennzahl;
    private float basiswert;
    private float modifier;

    @Before
    public void setUp(){
        basiswert = 0.7f;
        modifier = 0.25f;
        testkennzahl = new Kennzahl(basiswert, modifier);
        assertNotNull(testkennzahl);
    }


    @Test
    public void berechnen() throws Exception {
assertEquals(testkennzahl.berechnen(), basiswert + modifier, 0.5);
    }

    @Test
    public void addModifier() throws Exception {
        testkennzahl.addModifier(0.05f);
assertEquals(testkennzahl.getModifier(), modifier + 0.05f, 0.5);
    }

    @Test
    public void getBasiswert() throws Exception {
assertEquals(testkennzahl.getBasiswert(), basiswert, 0.5);
    }

    @Test
    public void setBasiswert() throws Exception {
testkennzahl.setBasiswert(0.5f);
assertEquals(testkennzahl.getBasiswert(), 0.5f, 0.5);
    }

    @Test
    public void getModifier() throws Exception {
        assertEquals(testkennzahl.getModifier(), modifier, 0.5);

    }

    @Test
    public void getWert() throws Exception {
        assertFalse(testkennzahl.getWert() == (basiswert + modifier));

    }

    @Test
    public void setWert() throws Exception {
        float vergleichsVar = testkennzahl.getWert();
        testkennzahl.setWert(0.5f);
        assertNotSame(testkennzahl.getWert(), vergleichsVar);
    }

    @After
    public void tearDown() throws Exception {

    }

}
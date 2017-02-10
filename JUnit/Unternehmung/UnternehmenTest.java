package Unternehmung;

import Unternehmung.Kennzahlen.Bilanz;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 15.01.2017.
 */
public class UnternehmenTest{

    @Test
    public void initDepartments() throws Exception {

    }

    @Test
    public void updateYearly() throws Exception {

    }

    @Test
    public void getName() throws Exception {

    }


    private Unternehmen testUnternehmen;
    private String passwort;
    private String name;
    private float eigenkapital;
    private transient Kennzahlensammlung kennzahlensammlung;

    private transient Bilanz bilanz;

    private transient Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

    @Before
    public void setUp(){
passwort = "12345";
name = "Test_Unternehmen";
eigenkapital = 500000;

        testUnternehmen = new Unternehmen( name, passwort, eigenkapital);
        assertNotNull(testUnternehmen);
    }

    @Test
    public void getPasswort() throws Exception {
assertEquals(testUnternehmen.getPasswort(), "12345");
    }

    @Test
    public void setPasswort() throws Exception {
        testUnternehmen.setPasswort("67890");
        assertEquals(testUnternehmen.getPasswort(), "67890");
    }

    @Test
    public void _getName() throws Exception {
        assertEquals(testUnternehmen.getName(), name);
    }

    @Test
    public void setName() throws Exception {
        String newName = "Stiftung GmbH";
        testUnternehmen.setPasswort(newName);
        assertEquals(testUnternehmen.getPasswort(), newName);
    }

    @Test
    public void getAbteilung() throws Exception {
        assertNotNull(testUnternehmen.getAbteilung("produktion"));
    }

    @Test
    public void getAbteilungen() throws Exception {
        assertNotNull(testUnternehmen.getAbteilungen());
    }

    @Test
    public void getKennzahlensammlung() throws Exception {
        assertNotNull(testUnternehmen.getKennzahlensammlung());
    }

    @After
    public void tearDown() throws Exception {

    }
}
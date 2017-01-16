package Unternehmung;

import Unternehmung.Kennzahlen.Bilanz;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by D064018 on 15.01.2017.
 */
public class UnternehmenTest extends TestCase {

    private Unternehmen testUnternehmen;
    private String passwort;
    private String name;
    private float eigenkapital;
    private transient Kennzahlensammlung kennzahlensammlung;

    private transient Bilanz bilanz;

    private transient Map<String,Abteilung> abteilungen = new HashMap<String,Abteilung>();

    public void setup(){
passwort = "12345";
name = "Test_Unternehmen";
eigenkapital = 500000;

    }

    @Before
    public void testCreateUnternehmen(){
testUnternehmen = new Unternehmen( name, passwort, eigenkapital);
        org.junit.Assert.assertNotNull(testUnternehmen);
    }


    @Test
    public void update() throws Exception {

    }

    @Test
    public void getPasswort() throws Exception {
assertEquals(testUnternehmen.getPasswort(), 12345);
    }

    @Test
    public void setPasswort() throws Exception {
        testUnternehmen.setPasswort("67890");
        assertEquals(testUnternehmen.getPasswort(), passwort);
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
       // assertEquals(testUnternehmen.getAbteilung());
    }

    @Test
    public void getAbteilungen() throws Exception {
       // assertEquals(testUnternehmen.getAbteilungen());
    }

    @Test
    public void getKennzahlensammlung() throws Exception {
        assertEquals(testUnternehmen.getKennzahlensammlung(), this.kennzahlensammlung);
    }

}
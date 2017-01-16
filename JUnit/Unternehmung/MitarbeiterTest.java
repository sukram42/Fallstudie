package Unternehmung;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 16.01.2017.
 */
public class MitarbeiterTest extends TestCase{

    private String name;
    private String vorname;
    private String adresse;
    private String imagelink;
    private Abteilung department;
    private char gender;
    private int gehalt;
    private int prodLeistung;

    private Mitarbeiter testMitarbeiter;

public void setUp(){
    name = "Peter";
    vorname = "Hans";
    adresse = "Humugstraße 12, 12470 Warstadt";
    imagelink = "erster Bildpfad";
  //  department = ;
    gender = 'm';
    gehalt = 60000;
}

    @Before
    public void createMitarbeiter(){
testMitarbeiter = new Mitarbeiter(name, vorname, adresse, imagelink, gender, gehalt);
        assertNotNull(testMitarbeiter);
    }

    @Test
    public void _getName() throws Exception {
        assertEquals( testMitarbeiter.getName(), name);
    }

    @Test
    public void setName() throws Exception {
String neuerName = "Schmidt";
testMitarbeiter.setName(neuerName);
assertEquals(testMitarbeiter.getName(), neuerName);

    }

    @Test
    public void getVorname() throws Exception {
assertEquals( testMitarbeiter.getVorname(), vorname);
    }

    @Test
    public void setVorname() throws Exception {
String neuerVorName = "Friedrich";
testMitarbeiter.setVorname(neuerVorName);
assertEquals(testMitarbeiter.getVorname(), neuerVorName);
    }

    @Test
    public void getAdresse() throws Exception {
assertEquals(testMitarbeiter.getAdresse(), adresse);
    }

    @Test
    public void setAdresse() throws Exception {
String neueAdresse = "Bertholdstraße 7, 19862 Bonnstadt";
testMitarbeiter.setAdresse(neueAdresse);
assertEquals(testMitarbeiter.getAdresse(), neueAdresse);
    }

    @Test
    public void getImagelink() throws Exception {
assertEquals( testMitarbeiter.getImagelink(), imagelink);
    }

    @Test
    public void setImagelink() throws Exception {
String neuerImagelink = "zweiter Bildpfad";
testMitarbeiter.setImagelink(neuerImagelink);
assertEquals(testMitarbeiter.getImagelink(), neuerImagelink);

    }

    @Test
    public void getDepartment() throws Exception {
        assertEquals( testMitarbeiter.getDepartment(), department);
    }

    @Test
    public void setDepartment() throws Exception {
//Abteilung neuesDepartment = ;
    }

    @Test
    public void getGender() throws Exception {
assertEquals(testMitarbeiter.getGender(), gender);
    }

    @Test
    public void setGender() throws Exception {
char neuesGender = 'z';
testMitarbeiter.setGender(neuesGender);
assertEquals(testMitarbeiter.getGender(), neuesGender);
    }

    @Test
    public void getGehalt() throws Exception {
assertEquals(testMitarbeiter.getGehalt(), gehalt);
    }

    @Test
    public void setGehalt() throws Exception {
int neuesGehalt = 10000;
testMitarbeiter.setGehalt(neuesGehalt);
assertEquals(testMitarbeiter.getGehalt(), neuesGehalt);
    }

    @Test
    public void getProdLeistung() throws Exception {
assertEquals(testMitarbeiter.getProdLeistung(), prodLeistung);
    }

    @Test
    public void setProdLeistung() throws Exception {
        int neueProLeistung = 150;
testMitarbeiter.setProdLeistung(neueProLeistung);
assertEquals(testMitarbeiter.getProdLeistung(), neueProLeistung);
    }

}
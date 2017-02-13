package Unternehmung.Abteilungen;

import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 16.01.2017.
 */
public class HRTest {

    private HR testHR;
    private Kennzahlensammlung kennzahlensammlung;
    private Unternehmen unternehmen;

    @Before
    public void setup() throws ZuWenigMitarbeiterException {
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
        kennzahlensammlung = unternehmen.getKennzahlensammlung();
        testHR = (HR)unternehmen.getAbteilung("hr");
        testHR.addMitarbeiter(1,10000);
        assertNotNull(testHR);
    }


    @Test
    public void starteProjekt() throws Exception {
        testHR.startProjekt("wifi");
        testHR.update();
        assertEquals(testHR.getKosten(), 10000, 0.01);
    }

    @Test
    public void stoppeProjekt() throws Exception {
        testHR.startProjekt("wifi");
        testHR.stoppeProjekt("wifi");
        testHR.update();
        assertEquals(testHR.getKosten(), 0, 0.01);
    }

    @Test
    public void changeProjectActivity() throws Exception {
        double wifiKosten = 500000 - 10000;
        testHR.startProjekt("wifi");
        testHR.changeProjectActivity("wifi");
        testHR.update();
        assertEquals(testHR.getKosten(), 0, 0.01);

    }

    @Test
    public void getProjects() throws Exception {
        assertEquals(testHR.getProjects().size(), 5);
    }


    @Test
    public void getTotalGehalt() throws Exception {
        //der Erste Vertriebler wird automatisch eingestellt
        assertEquals(testHR.getTotalGehalt(), (10000 + 2000)/12, 2);
}

    @Test
    public void getDurchschnittlichesGehalt() throws Exception {
        //der Erste Vertriebler wird automatisch eingestellt
        assertEquals(testHR.getDurchschnittlichesGehalt(), (10000 + 2000)/2/12, 0.5);
    }

    @Test
    public void getTotalMitarbeiterCount() throws Exception {
        //der Erste Vertriebler wird automatisch eingestellt
        assertEquals(testHR.getTotalMitarbeiterCount(), 2);
    }

    @Test
    public void getTotalMitarbeiter() throws Exception {
        //der Erste Vertriebler wird automatisch eingestellt
        unternehmen.getAbteilung("hr").getMitarbeiter().remove(0);
        assertEquals(testHR.getTotalMitarbeiter(), unternehmen.getAbteilung("vertrieb").getMitarbeiter());
    }

    @Test
    public void update() throws Exception {
        testHR.startProjekt("wifi");
        testHR.update();
        assertEquals(testHR.getKosten(), 10000, 0.01);
    }

    @Test
    public void kuendigeMitarbeiter()
    {
        try {
            testHR.addMitarbeiter(1,1000);
            assertEquals(testHR.getMitarbeiterAnzahl(),2);
            testHR.kuendigeMitarbeiter(testHR.getMitarbeiter().get(0));
            assertEquals(testHR.getMitarbeiterAnzahl(),1);
        } catch (ZuWenigMitarbeiterException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {

    }

}
package Unternehmung.Abteilungen.SozialeProjekte;

import Unternehmung.Unternehmen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by D064018 on 21.01.2017.
 */
public class ZeitGeldTest extends TestCase{

    private ZeitGeld testZeitGeld;

    @Before
    public void createTestZeitGeld(){
        Unternehmen unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
    testZeitGeld = new ZeitGeld("Urlaubsgeld", 150, 2,6, unternehmen);
    assertNotNull(testZeitGeld);
    }

    @Test
    public void update() throws Exception {
//ToDO
    }

}
package Unternehmung.Abteilungen.SozialeProjekte;

import Unternehmung.Objekte.ZeitGeld;
import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by D064018 on 21.01.2017.
 */
public class ZeitGeldTest {

    private ZeitGeld testZeitGeld;
    private Unternehmen unternehmen;

    @Before
    public void setUp() {
        unternehmen = new Unternehmen("Test_Unternehmen", "12345", 500000);
    }

    @Test
    public void constructor() {
        testZeitGeld = new ZeitGeld("Urlaubsgeld", 150, 2, 6, unternehmen);
        assertNotNull(testZeitGeld);
    }

    @Ignore
    public void update() throws Exception {
//ToDO
    }

    @After
    public void tearDown() throws Exception {

    }

}
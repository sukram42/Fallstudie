package Unternehmung.Objekte;

import Unternehmung.Unternehmen;
import org.junit.After;
import org.junit.Before;

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
        testZeitGeld = new ZeitGeld("Urlaubsgeld", 150, 2, 6, unternehmen);
        assertNotNull(testZeitGeld);
    }

    @After
    public void tearDown() throws Exception {

    }

}
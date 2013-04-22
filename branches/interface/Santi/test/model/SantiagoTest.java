/**
 * 
 */
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Chris
 * 
 */
public class SantiagoTest {

    private Santiago santiago;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        santiago = new Santiago();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link model.Santiago#setNiveauPartie()}.
     */
    @Test
    public void testSetInitNiveauPartieFacile1() {
        santiago.setNiveauPartie(NiveauPartie.FACILE);
        assertFalse(santiago.isAvecPalmier());
    }

    /**
     * Test method for {@link model.Santiago#setNiveauPartie()}.
     */
    @Test
    public void testSetInitNiveauPartieFacile2() {
        santiago.setNiveauPartie(NiveauPartie.FACILE);
        int attendu = 0;
        assertEquals(attendu, santiago.getNiveau());
    }

    /*
     * ... On peut tester tous les niveaux
     */

}

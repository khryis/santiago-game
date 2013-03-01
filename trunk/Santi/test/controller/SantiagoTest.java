/**
 * 
 */
package controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// TEST Antho --> Chris ; Flo --> Antho ; Chris --> Flo  

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
     * Test method for {@link controller.Santiago#configurer()}.
     * 
     */
    @Test
    public void testConfigurer() {
        fail("Not yet implemented"); // TODO
    }
    
    /**
     * 
     */
    
    
    /**
     * Test method for {@link controller.Santiago#initJoueur()}.
     */
    @Test
    public void testInitJoueur() {
        // TODO
        santiago.initJoueur();
        assertNotNull(santiago.getListJoueurs());
        fail("Not yet implemented");
    }
    
    /**
     * Test method for {@link controller.Santiago#niveauPartie()}.
     */
    @Test
    public void testNiveauPartie() {
        // TODO niveau partie
        // niveau égal à 0 ou 1
        fail("Not yet implemented"); 
    }
    
    /**
     * Test method for {@link controller.Santiago#encherirCarte()}.
     */
    @Test
    public void testMiseAuxEncheres() {
        // TODO mise au enchère 
        // HashMap de Joueur pas vide
        // vérifier qu'il y a toujours un constructeur
        fail("Not yet implemented"); 
    }
    
    /**
     * Test method for {@link controller.Santiago#encherirCarte()}.
     */
    @Test
    public void testPlacementDesPlantations() {
        // TODO placement plantations
        // HashMap doitêtre vide à la fin
        // les cartes dévoilés doivent avoir été affectés sur le plateau 
        // et à des joueurs
        // plus commentaire méthode
        fail("Not yet implemented"); 
    }
    
    /**
     * Test method for {@link controller.Santiago#enchereMax(java.util.HashMap)}.
     */
    @Test
    public void testEnchereMax() {
        // TODO enchère MAx Antho Flo
        // le max de la HashMap
        fail("Not yet implemented"); 
    }
    
    /**
     * Test method for {@link controller.Santiago#soudoyerConstructeur()}.
     */
    @Test
    public void testSoudoyerConstructeur() {
        // TODO 
        fail("Not yet implemented"); 
    }
    
    /**
     * Test method for {@link controller.Santiago#irrigationSupplementaire()}.
     */
    @Test
    public void testIrrigationSupplementaire() {
        fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for {@link controller.Santiago#secheresse()}.
     */
    @Test
    public void testSecheresse() {
        fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for {@link controller.Santiago#diaDePaga()}.
     */
    @Test
    public void testDiaDePaga() {
        fail("Not yet implemented"); // TODO
    }
    
}

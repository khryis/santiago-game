package model;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PlateauTest {
    
    private Plateau plateau;
    private int niveau;
    private int nbJoueur;
    
    @Parameters 
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{ {0,3}, {0,4}, {0,5}, {1,3}, {1,4}, {1,5} });
    }    
        
    public PlateauTest( int niveau, int nbJoueur) {
        this.niveau = niveau; 
        this.nbJoueur = nbJoueur; 
    }
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        this.plateau = new Plateau(this.niveau, this.nbJoueur);
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testPlacerPalmier() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testTirerCarte() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testPoserUneCarte() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testPlacerCanal() {
        
        
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testPlacerCanalSup() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testSecheresse() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testMajIrrigationTotale() {
        fail("Not yet implemented"); // TODO
    }
    
    @Test
    public void testMajIrrigation1Carte() {
        fail("Not yet implemented"); // TODO
    }
    
}

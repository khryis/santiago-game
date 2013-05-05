package model;

import static org.junit.Assert.assertEquals;
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
    private final NiveauPartie niveau;
    private final int nbJoueur;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { NiveauPartie.FACILE, 3 }, { NiveauPartie.FACILE, 4 }, { NiveauPartie.FACILE, 5 }, { NiveauPartie.MOYEN, 3 }, { NiveauPartie.MOYEN, 4 },
                { NiveauPartie.MOYEN, 5 }, { NiveauPartie.DIFFICILE, 3 }, { NiveauPartie.DIFFICILE, 4 }, { NiveauPartie.DIFFICILE, 5 } });
    }

    public PlateauTest(NiveauPartie niveau, int nbJoueur) {
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
    public static void testPlacerPalmier() {
        fail("Not yet implemented");
    }

    @Test
    public static void testTirerCarte() {
        fail("Not yet implemented");
    }

    @Test
    public static void testPoserUneCarte() {
        fail("Not yet implemented");
    }

    @Test
    public void testPlacerCanal1() {
        // voir si canal pas présent dans la liste tant que l'on ne l'as pas
        // posé
        int valeurAttendu = -1;
        int index = this.plateau.getCanaux().indexOf(new PositionSegment(0, 0, 2, 0));
        assertEquals(valeurAttendu, index);
    }

    @Test
    public void testPlacerCanal2() {
        // test si place bien le canal dans la liste et a occupé
        boolean valeurAttenduOccupe = true;
        this.plateau.placerCanal(0, 0, 2, 0);
        int index = this.plateau.getCanaux().indexOf(new PositionSegment(0, 0, 2, 0));
        assertEquals(valeurAttenduOccupe, this.plateau.getCanaux().get(index).isOccupe());
    }

    @Test
    public static void testPlacerCanalSup() {
        fail("Not yet implemented");
    }

    @Test
    public static void testSecheresse() {
        fail("Not yet implemented");
    }

    @Test
    public static void testMajIrrigationTotale() {
        fail("Not yet implemented");
    }

    @Test
    public static void testMajIrrigation1Carte() {
        fail("Not yet implemented");
    }

}

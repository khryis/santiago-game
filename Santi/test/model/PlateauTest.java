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
	private final int niveau;
	private final int nbJoueur;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 0, 3 }, { 0, 4 }, { 0, 5 },
				{ 1, 3 }, { 1, 4 }, { 1, 5 } });
	}

	public PlateauTest(int niveau, int nbJoueur) {
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
		// TODO placer palmier
		fail("Not yet implemented");
	}

	@Test
	public void testTirerCarte() {
		// TODO tirer carte Chris --> Antho
		fail("Not yet implemented");
	}

	@Test
	public void testPoserUneCarte() {
		// TODO poser une carte
		fail("Not yet implemented");
	}

	@Test
	public void testPlacerCanal1() {
		// voir si canal pas présent dans la liste tant que l'on ne l'as pas
		// posé
		int valeurAttendu = -1;
		int index = this.plateau.getCanaux().indexOf(
				new PositionSegment(0, 0, 2, 0));
		assertEquals(valeurAttendu, index);
	}

	@Test
	public void testPlacerCanal2() {
		// test si place bien le canal dans la liste et a occupé
		boolean valeurAttenduOccupe = true;
		this.plateau.placerCanal(0, 0, 2, 0);
		int index = this.plateau.getCanaux().indexOf(
				new PositionSegment(0, 0, 2, 0));
		assertEquals(valeurAttenduOccupe, this.plateau.getCanaux().get(index)
				.isOccupe());
	}

	@Test
	public void testPlacerCanalSup() {
		// TODO placer canalSup
		fail("Not yet implemented");
	}

	@Test
	public void testSecheresse() {
		// TODO secheresse
		fail("Not yet implemented");
	}

	@Test
	public void testMajIrrigationTotale() {
		// TODO irrigationTotale
		fail("Not yet implemented");
	}

	@Test
	public void testMajIrrigation1Carte() {
		// TODO irrigatin 1 carte
		fail("Not yet implemented");
	}

}

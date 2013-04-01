/**
 * 
 */
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import model.Santiago;

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
	 * Test method for {@link model.Santiago#initJoueur()}.
	 */
	@Test
	public void testInitJoueur() {
		santiago.initJoueur();
		assertNotNull(santiago.getListJoueurs());
	}

	/**
	 * Test method for {@link model.Santiago#niveauPartie()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNiveauPartie1() throws IOException {
		// FIXME simuler la saisie utilisateur...
		// niveau égal à 0 et sans Palmier
		boolean valeurAttenduPalmier = false;
		int valeurAttenduNiveau = 0;
		// BufferedReader bf = Mockito.mock(BufferedReader.class);
		// Mockito.when(bf.readLine()).thenReturn("1");
		this.santiago.niveauPartie();
		assertEquals(valeurAttenduPalmier, this.santiago.isAvecPalmier());
		assertEquals(valeurAttenduNiveau, this.santiago.getNiveau());
		// Assert.when(new
		// Client(bufferedReader).parseLine()).thenEquals(IsEqual.equalTo("1"));
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Santiago#niveauPartie()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNiveauPartie2() throws IOException {
		// niveau égal à 0 et avec Palmier
		boolean valeurAttenduPalmier = true;
		int valeurAttenduNiveau = 0;
		// BufferedReader bf = Mockito.mock(BufferedReader.class);
		// when(bf.readLine()).thenReturn("2");
		this.santiago.niveauPartie();
		assertEquals(valeurAttenduPalmier, this.santiago.isAvecPalmier());
		assertEquals(valeurAttenduNiveau, this.santiago.getNiveau());
		// Assert.when(new
		// Client(bufferedReader).parseLine()).thenEquals(IsEqual.equalTo("1"));
		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Santiago#niveauPartie()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNiveauPartie3() throws IOException {
		// niveau égal à 1 et avec Palmier
		boolean valeurAttenduPalmier = true;
		int valeurAttenduNiveau = 1;
		// BufferedReader bf = Mockito.mock(BufferedReader.class);
		// when(bf.readLine()).thenReturn("3");
		this.santiago.niveauPartie();
		assertEquals(valeurAttenduPalmier, this.santiago.isAvecPalmier());
		assertEquals(valeurAttenduNiveau, this.santiago.getNiveau());
		// Assert.when(new
		// Client(bufferedReader).parseLine()).thenEquals(IsEqual.equalTo("1"));
		// fail("Not yet implemented");
	}

}

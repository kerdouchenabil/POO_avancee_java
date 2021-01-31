package pobj.motx.tme3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class GrilleSolverTest {
	
	
	/*
	@Test
	public void testHard() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		// System.out.println(gp);
		// assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
		CSPSolver solver = new CSPSolver();

		// solver.setStrat(new StratFirst());
		// solver.setStrat(new StratMin());
		
		long timestamp = System.currentTimeMillis();
		ICSP solution = solver.solve(problem);

		System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}

	
	@Test
	public void testSplit() {
		
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/split.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(6, gr.nbLig());

		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);


		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
	}
	
	@Test
	public void testEasy2() {
		
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy2.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());
		
		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);
		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
	}
	@Test
	public void testMakeEasy2() {
		
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());
		
		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);
		
		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
		
	}	
	
	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());
		
		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
		
	}
	
	
	@Test
	public void testLarge2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large2.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
	}
	
	
	@Test
	public void testLarge3() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large3.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		//System.out.println("Avant résolution de la grille: ");
		//System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
	}
	*/
	
	@Test
	public void testLarge4() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large4.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		System.out.println("Avant résolution de la grille: ");
		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		MotX varsx = new MotX(gp);

		CSPSolver csp = new CSPSolver();
		MotX v = (MotX) csp.solve(varsx);
		
		System.out.println("Après résolution de la grille: ");
		System.out.println(v.getGp().getGrillePlaces().toString());
	}
	
	
}

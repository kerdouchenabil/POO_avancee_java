package pobj.multiset.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.InvalidCountException;
import pobj.multiset.MultiSet;
import pobj.multiset.MultiSetDecorator;

public class HashMultiSetTest2 {
	
	/**
	 * teste les fonctions add
	 * @throws InvalidCountException
	 */
	@Test
	public void testAdd1() throws InvalidCountException {
		//MultiSet<String> m = new HashMultiSet<>();
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>());
		m.add("a");
		m.add("a",5);
		assertEquals(m.count("a"), 6);
	}

	/**
	 * teste si la fonction add renvoi une exception si argument négatif
	 * @throws InvalidCountException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2() throws InvalidCountException {
		//MultiSet<String> m = new HashMultiSet<>();
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>());
		m.add("a");
		m.add("a",-1);
	} 
	
	/**
	 * teste les fonctions remove
	 * @throws InvalidCountException
	 */
	@Test
	public void testRemove1() throws InvalidCountException {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");

		//MultiSet<String> m = new HashMultiSet<>(list);
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>(list));

		m.remove("a");
		m.remove("a",1);
		assertEquals(m.count("a"),0);
		assertEquals(m.count("b"), 1);
	} 
	
	/**
	 * teste si la fonction remove renvoi une exception si argument négatif
	 * @throws InvalidCountException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemove2() throws InvalidCountException {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("a");

		//MultiSet<String> m = new HashMultiSet<>(list);
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>(list));

		m.remove("a");
		m.remove("a",-1);
	} 
	
	
	/**
	 * teste la fonction size
	 * @throws InvalidCountException
	 */
	@Test
	public void testSize() throws InvalidCountException {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("a");

		//MultiSet<String> m = new HashMultiSet<>(list);
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>(list));

		assertEquals(m.size(), 3);
	}
	
	
	/**
	 * teste le retour de toString
	 * @throws InvalidCountException
	 */
	@Test
	public void testToString() throws InvalidCountException {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("a");

		//MultiSet<String> m = new HashMultiSet<>(list);
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>(list));
		assertEquals(m.toString(),"HashMultiSet [a:2; b:1]");
	}
	
	/**
	 * teste la fonction clear
	 * @throws InvalidCountException
	 */
	@Test
	public void testClear() throws InvalidCountException {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("a");

		//MultiSet<String> m = new HashMultiSet<>(list);
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>(list));

		m.clear();
		assertTrue(m.isEmpty());
		assertEquals(m.size(),0);
	}	
	/**
	 * teste plusieur fois les fonctions add et remove 
	 * @throws InvalidCountException
	 */
	@Test
	public void testAddRemove() throws InvalidCountException{
		//MultiSet<String> m = new HashMultiSet<>();
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>());

		m.add("a");
		m.add("a",5);
		m.remove("a");
		m.remove("a",1);
		m.add("b",10);
		m.remove("a",3);
		m.remove("b",3);
		assertEquals(m.count("a"),1);
		assertEquals(m.count("b"),7);		
	}
	
	
	/**
	 * teste les fonctions add, remove, count avec l'argument '0' 
	 * @throws InvalidCountException
	 */
	@Test
	public void testAddRemoveCount() throws InvalidCountException{
		//MultiSet<String> m = new HashMultiSet<>();
		MultiSet<String> m = new MultiSetDecorator<String>(new HashMultiSet<>());

		m.add("a",0);
		assertEquals(m.count("a"), 0);
		m.remove("a",0);
		assertEquals(m.count("a"), 0);
		assertEquals(m.count("c"), 0);
		
		
		System.out.println("all tests passed!");
	}
	
}

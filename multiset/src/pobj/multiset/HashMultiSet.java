package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	private HashMap<T, Integer> multi ;
	private int size=0;
	public HashMultiSet() {
		this.multi = new HashMap<>();
	}
	
	public HashMultiSet(Collection<T> col){
		Object[] tab = col.toArray();
		multi = new HashMap<T,Integer>();
		for(Object o : tab) {
			T o2 = (T) o;
			if(multi.get(o2)!=null) {
			multi.put(o2, this.count(o2)+1);
			}else {
				multi.put(o2,1);

			}
			size++;
		}
	}
	
	@Override
	public boolean add(T e, int count) {
		
		if(count < 0) throw new IllegalArgumentException("le nombre d'occurences a ajouter doit etre positif");
		
		if(count == 0) {
			assert this.isConsistent();
			return false ;
		}
		Integer v = multi.get(e);
		if(v != null) {
			multi.put(e, v+count);
			size=size+count;
			
		}else {
			multi.put(e, count);
			size=size+count;
			
		}
		assert this.isConsistent();
		return true;
		
	}

	@Override
	public boolean add(T e) {
		Integer v = multi.get(e);
		if(v != null) {
			multi.put(e, v+1);
			size++;
			

		}else {
			multi.put(e, 1);
			size++;
			
		}
		assert this.isConsistent();
		return true;
	}

	@Override
	public boolean remove(Object e) {
		
		Integer v =multi.get(e);
		if(v != null) {
			if(v == 1) {
				multi.remove(e);
				size--;
				assert this.isConsistent();
				return true;
			}else {
					T t = (T) e;
					multi.put(t, v-1);
					size--;
					assert this.isConsistent();
					return true;
			}
		}
		assert this.isConsistent();
		return false;
	}

	@Override
	public boolean remove(Object e, int count) {
		
		if(count < 0) throw new IllegalArgumentException("le nombre d'occurences a supprimer doit etre positif");
		
		if(count == 0 ) {
			assert this.isConsistent();
			return false ;
		
		}
		Integer v =multi.get(e);
		if(v != null) {
			if(v == 1) {
				multi.remove(e);
				size=size-1;
				assert this.isConsistent();
				return true;
			}else {
				if(v>count) {
					T t = (T) e;
					multi.put(t, v-count);
					size =size-count;
					assert this.isConsistent();
					return true;
				}else {
					multi.remove(e);
					size=size - v;
					assert this.isConsistent();
					return true;
				}
			}
		}else {
			assert this.isConsistent();
			return false ;
		}
		
	}

	@Override
	public int count(T o) {
		Integer v = multi.get(o);
		if(v != null) {
			return v ;
		}
		return 0;
	}

	@Override
	public void clear() {
		multi.clear();
		this.size = 0 ;
	}

	@Override
	public int size() {
		return this.size ;
	}
	
	@Override
	public List<T> elements() {
		Set<T> set = new HashSet<>(new ArrayList<T>(this)); //sans doublons
		return new ArrayList<T>(set);
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator();
	}
	
	//classe interne
	private class HashMultiSetIterator implements Iterator<T>{
		
		private Iterator<Map.Entry<T, Integer>> mapIter;
		private int cpt; //nombre de fois qu'on a retourné l'element actuel
		private Map.Entry<T, Integer> courant; //element courant (tuple)
		
		private HashMultiSetIterator() {
			mapIter = multi.entrySet().iterator();
			if(mapIter.hasNext()) {
				courant = mapIter.next();
				cpt = 0;
			}
			else {
				cpt=-1; //vide (courant=null)
			}
			
		}

		@Override
		public boolean hasNext() {
			
			if(cpt==-1) { //vide
				return false;
			}
			if(mapIter.hasNext()) {
				return true;
			}
			//dernier tuple
			int nbOccur = courant.getValue();
			return cpt<nbOccur;
		}

		@Override
		public T next() {
			if( hasNext() ) {
				int nbOccur = courant.getValue();
				if(cpt<nbOccur) {
					//retourner le meme element
					cpt++;
					return courant.getKey();
				}
				else { //cpt==nbOccur
					courant = mapIter.next();//passe au suivant
					cpt = 1;//on retourne une occurence
					return courant.getKey();
				}
			}
			
			throw new NoSuchElementException();
		}
		
	}
	
	
	public Comparator<T> getComparator() {
		return new HashMultiSetComparator();
	}
	
	private class HashMultiSetComparator implements Comparator<T>{
		
		@Override
		public int compare(T t1, T t2) {
			int nb1 = multi.get(t1);
			int nb2 = multi.get(t2);
			/* attention: inversé pour ordre décroissant */
			if(nb1<nb2) return 1;
			if(nb1>nb2) return -1;
			return 0;
		}
	}
	
	/* --- tme5 --- */
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("HashMultiSet [");
		
		List<T> list = this.elements();
		int i = 0;
		for( T elem : list ) {
			
			int nb = this.count(elem);
			if(i!=list.size()-1) {
			b.append( elem+":"+nb+"; " );
			}else {
				b.append( elem+":"+nb);
			}
			i++;
		}
		return b.toString()+"]";
	}
	
	public boolean isConsistent() {
		int i=0 ;
		for( Map.Entry<T, Integer> ent : multi.entrySet()) {
			int nb = ent.getValue();
			if(nb <= 0) {return false;}
			i+=nb;

		}
		if(i!=this.size()) {return false;}
		return true;
	}
	
}

	
package pobj.multiset;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {
	private MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> d) {
		decorated =d;
	}
	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public boolean add(T e, int count) throws InternalError {
		
			boolean b = decorated.add(e, count);
			if(!decorated.isConsistent()) {
				throw new InternalError();
			}
			return b;
	}

	@Override
	public boolean add(T e) throws InternalError {
		boolean b = decorated.add(e);
		if(!decorated.isConsistent()) {
			throw new InternalError();
		}
		return b;
	}

	@Override
	public boolean remove(Object e) throws InternalError {
		boolean b = decorated.remove(e);
		if(!decorated.isConsistent()) {
			throw new InternalError();
		}
		return b;	}

	@Override
	public boolean remove(Object e, int count) throws InternalError {
		boolean b = decorated.remove(e, count);
		if(!decorated.isConsistent()) {
			throw new InternalError();
		}
		return b;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public void clear() {
		decorated.clear();
	}

	@Override
	public int size() {
		return decorated.size();
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Comparator<T> getComparator() {
		return decorated.getComparator();
	}
	@Override
	public boolean isConsistent() {
		return decorated.isConsistent();
	
	}
	@Override
	public String toString() {
		return decorated.toString();
	}

}

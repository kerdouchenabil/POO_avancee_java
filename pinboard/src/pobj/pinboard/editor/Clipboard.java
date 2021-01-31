package pobj.pinboard.editor;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import pobj.pinboard.document.Clip;

public class Clipboard {
	
	private static Clipboard cb;
	private List<Clip> press;
	private Set<ClipboardListener> lis;

	private Clipboard() {
		
		press = new ArrayList<>();
		lis = new HashSet<>();
		cb = null;
		
	}

	public void copyToClipboard(List<Clip> clips) {
		clear();
		for (Clip c : clips) {
			press.add(c.copy());
		}
			
		notifyListener();
	}

	public List<Clip> copyFromClipboard() {
		
		List<Clip> r = new ArrayList<>();
		for (Clip c : press) {
			r.add(c.copy());
		}
			
		return r;

	}

	public void clear() {
		press.clear();
		notifyListener();
	}

	public boolean isEmpty() {
		return press.isEmpty();
	}

	public static Clipboard getInstance() {
		if (cb == null) cb = new Clipboard();
		return cb;
	}

	private void notifyListener() {
		for (ClipboardListener listener : lis)
			listener.clipboardChanged();
	}
	public void removeListener(ClipboardListener listener) {
		lis.remove(listener);
	}
	public void addListener(ClipboardListener listener) {
		lis.add(listener);
	}


}

package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {
	
	public static void wordcount (MultiSet<String> ms) throws IOException {
		//chargement du fichier et decoupage en mots
		String file = "data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine())!=null) {
			for (String word : line.split("\\P{L}+")) {
				if (word.equals("")) continue; // ignore les mots vides
				ms.add(word);
			}
		}
		br.close(); 
		
		//liste sans doublons
		List<String> list = ms.elements();
		
		//trie (besoin de comparaison)
		list.sort(ms.getComparator());
		
		//affichage
		int nbAffich = 10;
		if(nbAffich>list.size()) nbAffich=list.size();
		
		String s = "";
		for (int i=0; i<nbAffich; i++) {
			s += list.get(i)+"  (" + ms.count(list.get(i))+" fois)\n";
		}
		System.out.println(s);
		
		System.out.println("--- tme5 ---");
		
		System.out.println(ms.toString());
		
	}
	
	
	public static void main(String args[]) {
		
		HashMultiSet<String> h = new HashMultiSet<String>();
		///// pour activer les tests
		//MultiSetDecorator<String> h = new MultiSetDecorator<String>(new HashMultiSet<String>());
		/////
		System.out.println("---HashMultiSet---");
		Chrono chrono = new Chrono(); 
		try {
			wordcount(h);
		} catch (IOException e) {
			e.printStackTrace();
		}
		chrono.stop();
		
		
		
		/// tests sur NaiveMultiset (prend trop de temps 15min+ ///
		/*
		System.out.println("---NaiveMultiSet---");
		NaiveMultiSet<String> naive = new NaiveMultiSet<String>();
		//MultiSetDecorator<String> naive = new MultiSetDecorator<String>(new NaiveMultiSet<String>());
		Chrono chronoNaive = new Chrono();
		try {
			wordcount(naive);
		} catch (IOException e) {
			e.printStackTrace();
		}
		chronoNaive.stop();
		*/
		
	}
	
	
}

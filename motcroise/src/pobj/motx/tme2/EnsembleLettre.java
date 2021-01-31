package pobj.motx.tme2;
import java.util.ArrayList;
import java.util.List;

/**
 * definit un ensemble (sans doublons) de lettres, aves des methodes pour les manipuler
 */
public class EnsembleLettre {
	
	/**
	 * liste de lettres (caracteres)
	 */
	private List<Character> lettres;
	
	/**
	 * Constructeur
	 * initialise la liste des lettres vide
	 */
	public  EnsembleLettre () {
		lettres = new ArrayList<Character>();
	}
	
	/**
	 * ajoute une lettre sans doublons
	 * @param charactere (lettre)
	 */
	public void add(char c) {
		if( ! lettres.contains(c)) {
			lettres.add(c);
		}
	}
	
	/**
	 * @return nombre de lettres dans l'ensemble
	 */
	public int size() {
		return lettres.size();
	}
	
	/**
	 * @param caractere
	 * @return vrai s'il existe dans l'ensemble, faux sinon
	 */
	public boolean contains(char c) {
		return lettres.contains(c);
	}
	
	/**
	 * redéfinition de clone pour cloner un ensemble de lettres
	 */
	@Override
	public EnsembleLettre clone() {
		EnsembleLettre e = new EnsembleLettre();
		for(Character c : lettres) {
			e.add(c);
		}
		return e;
	}
	
	/**
	 * @param ensembleLettre e
	 * @return lettres communes (intersection)
	 */
	public EnsembleLettre intersect(EnsembleLettre e) {
		//peut etre codée avec retainAll:
		EnsembleLettre el = this.clone();
		el.lettres.retainAll(e.lettres);
		return el;
		
		//autre solution possible:
		/*
		EnsembleLettre lettres_intersect = new EnsembleLettre();
		for(Character c : lettres) {
			if(e.contains(c)) {
				lettres_intersect.add(c);
			}
		}
		return lettres_intersect;
		*/
	}
	
}

package pobj.motx.tme1;
import java.util.ArrayList;
import java.util.List;;
/**
 * Représente un emplacement d'un mot, donc une liste de cases (lettres)
 */
public class Emplacement {
	
	/** liste des cases contenant les lettres du mot */
	private List<Case> lettres ;
	
	/** Constructeur, initialise l'emplacement (liste vide) */
	public Emplacement () {
		lettres = new ArrayList<Case>();
	}
	
	/**
	 * retourne la representation du mot (chaine de caracteres)
	 */
	@Override public String toString() {
		String s = "";
		for (Case c : this.lettres) {
			s += c.getChar();
		}
		return s;
	}
	
	/**
	 * @return la taille du mot
	 */
	public int size() {
		return lettres.size();
	}
	
	/**
	 * @param indice de la lettre
	 * @return la lettre (Case) correspondant a l'indice donné
	 */
	public Case getCase(int i) {
		return lettres.get(i);
	}
	
	/**
	 * ajoute une case (lettre) a la liste (ajoute en fin)
	 * @param la case a ajouter (une lettre)
	 */
	public void addCase(Case c) {
		this.lettres.add(c);
	}
	
	public List<Case> getCases(){
		return lettres;
	}
	
	/*tme3*/
	/**
	 * @return vrai s'il y a au moins une case vide
	 */
	public boolean hasCaseVide() {
		for(Case c : lettres) {
			if(c.isVide())
				return true;
		}
		return false;
	}
	
}

package pobj.motx.tme1;

/**
 * Represente une case pouvant etre pleine, vide ou contenant une lettre
 */
public class Case {
	
	/**
	 * numero (indice) de la ligne, colonne de cette case au sein de la grille
	 */
	private int lig, col;
	/** contenu de la case */
	private char c;
	
	/**
	 * @param ligne de cette case
	 * @param colonne de cette case
	 * @param contenu
	 */
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.c = c;
	}

	/**
	 * @return ligne de la case
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * @return colonne de la case
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return contenu de la case
	 */
	public char getChar() {
		return c;
	}

	/**
	 * modifie le contenu de la case
	 * @param caractere
	 */
	public void setChar(char c) {
		this.c = c;
	}
	
	/**
	 * teste si la case est vide
	 * @return true/false
	 */
	public boolean isVide() { //case vide= blanche
		return c == ' ';
	}
	
	/**
	 * teste si la case est pleiness
	 * @return true/false
	 */
	public boolean isPleine() { //case pleine= noire
		return c == '*';
	}
	
	
}

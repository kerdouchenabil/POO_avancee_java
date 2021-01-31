package pobj.motx.tme1;

/**
 * represente une grille de mots croises
 */
public class Grille {
	
	/**
	 * matrice de Cases
	 */
	private Case[][] mat;
	
	/**
	 * initialise toutes les cases vides (blanches)
	 * avec des coords qui commencent de 0 à hauteur/largeur
	 * @param hauteur de la grille
	 * @param largeur de la grille
	 */
	public Grille(int hauteur, int largeur) {
		mat = new Case[hauteur][largeur];
		for(int i=0; i<hauteur; i++) {
			for(int j=0; j<largeur; j++) {
				mat[i][j] = new Case(i, j, ' ');
			}
		}
	}
	
	/**
	 * @param ligne
	 * @param colonne
	 * @return Case correspondante
	 */
	public Case getCase(int lig, int col) {
		return mat[lig][col];
	}
	
	/**
	 * retourne la representation de la grille (chaine de caracteres)
	 */
	public String toString() {
		return GrilleLoader.serialize(this, false); //isGrlFormat=false
	}
	
	/**
	 * @return nombre de lignes de la grille
	 */
	public int nbLig() {
		return mat.length;
	}
	
	/**
	 * @return nombre de colonnes de la grille
	 */
	public int nbCol() {
		return mat[0].length;
	}
	
	/**
	 * @return une copie de la grille
	 */
	public Grille copy() {
		int lig = this.nbLig();
		int col = this.nbCol();
		Grille g = new Grille(lig, col);
		
		for(int i=0; i<lig; i++) {
			for(int j=0; j<col; j++) {
				g.mat[i][j] = new Case(i, j, this.getCase(i,j).getChar());
			}
		}
		
		return g;
	}
	
}

package pobj.motx.tme2;

/**
 * Classe représentant les contraintes liées aux croisement des mots de la grille
 */
public class CroixContrainte implements IContrainte{
	
	/**
	 * ((m1, c1),(m2, c2)), donnant l’indice m1 du premier emplacement et l’indice c1 de la case où a lieu ce croisement pour cet emplacement,
	 * et les indices correspondant (m2, c2) pour le deuxième emplacement.
	 */
	private int m1, c1, m2, c2;
	
	/**
	 * Constructeur
	 * @param m1 emplacement 1
	 * @param c1 case 1
	 * @param m2 emplacement 2
	 * @param c2 case 2
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1 = m1;
		this.c1 = c1;
		this.m2 = m2;
		this.c2 = c2;
	}
	
	/**
	 * Réduit le nombre de mots possibles pour les emplacements de la grille en tenant compte des contraintes de croisement
	 */
	@Override
	public int reduce(GrillePotentiel grille) {
		int cpt=0; //nombre de mots supprimés
		//calcul des ensembles des lettres l1 et l2 (méthodes dans Dictionnaire.java)
		EnsembleLettre l1 = grille.getMotsPot().get(m1).getEnsembleLettre(c1);
		EnsembleLettre l2 = grille.getMotsPot().get(m2).getEnsembleLettre(c2);
		EnsembleLettre s = l1.intersect(l2);

		if(l1.size() > s.size() && l2.size() > s.size()){
			cpt= grille.getMotsPot().get(m1).filtreIndice(s,c1) + grille.getMotsPot().get(m2).filtreIndice(s,c2);
		}else if(l2.size() > s.size()) {
			cpt= grille.getMotsPot().get(m2).filtreIndice(s,c2);
		}else {
			cpt= grille.getMotsPot().get(m1).filtreIndice(s,c1);
		}
		
		return cpt;
	}
	
	
	/**
	 * redéfinition de equals entre deux CroixContraintes
	 */
	@Override
	public boolean equals (Object other) {
		if(other == null) {return false ;}
		
		if (other == this) {return true ;}
		
		if(!(other instanceof CroixContrainte)) {return false;}
		
		CroixContrainte obj =(CroixContrainte)other;
		
		if(this.m1 != obj.m1 || this.c1 != obj.c1 
				|| this.m2 != obj.m2 ||this.c2 != obj.c2 ) {
			
			return false;
		}
		return true ;
	}
	
}

package pobj.motx.tme1;
import java.util.ArrayList;
import java.util.List;

/**
 * Explore une grille et trouve les emplacements des mots
 */
public class GrillePlaces {
	
	/** liste qui contient les Emplacements des mots trouvés */
	private List<Emplacement> places;
	
	/** grille a explorer */
	private Grille grille;
	
	/** nombre d'emplacements horizontaux */
	private int nbHoriz;
	
	/** nombre d'emplacements verticaux */
	private int nbVerti;
	
	/**
	 * Constructeur, remplit la liste des emplacements trouvés dans la grille
	 * @param grille
	 */
	public GrillePlaces (Grille grille) {
		this.grille = grille;
		places = new ArrayList<Emplacement>();
		
		for(int i=0; i<grille.nbLig(); i++) { //parcours les lignes
			this.cherchePlaces(this.getLig(i));
		}
		nbHoriz = places.size();
		for(int i=0; i<grille.nbCol(); i++) { //parcours les colonnes
			this.cherchePlaces(this.getCol(i));
		}
		nbVerti = places.size() - nbHoriz;
	}
	
	/**
	 * @return liste des emplacements
	 */
	public List<Emplacement> getPlaces(){
		return this.places;
	}
	
	/**
	 * @return nombre d'emplacements horizontaux
	 */
	public int getNbHorizontal() {
		return nbHoriz;
	}
	public int getNbVertical() {
		return nbVerti;
	}
	public int size() {
		return places.size();
	}
	
	/**
	 * retourne la representation des emplacements
	 */
	public String toString() {
		String s = "--Emplacements des mots--\nHorizontaux:\n";
		for (int i=0; i<this.getNbHorizontal(); i++) {
			s += places.get(i).toString() + "\n";
		}
		s += "Verticaux:\n";
		for (int i=this.getNbHorizontal(); i<places.size(); i++) {
			s += places.get(i).toString() + "\n";
		}
		return s;
	}
	
	/**
	 * @param numero (indice) de la ligne
	 * @return liste des cases de la ligne
	 */
	private List<Case> getLig (int lig){
		List<Case> cases = new ArrayList<Case>();
		for(int i=0; i<grille.nbCol(); i++) {
			cases.add(grille.getCase(lig, i));
		}
		return cases;
	}
	
	
	/**
	 * @param numero (indice) de la colonne
	 * @return liste des cases de la colonne
	 */
	private List<Case> getCol(int col){
		List<Case> cases = new ArrayList<Case>();
		for(int i=0; i<grille.nbLig(); i++) {
			cases.add(grille.getCase(i, col));
		}
		return cases;
	}
	
	/**
	 * cherche et ajoute les emplacements trouvés a la liste (places)
	 * @param liste des cases a chercher pour trouver des emplacements
	 */
	private void cherchePlaces(List<Case> cases) {
		Emplacement emp = new Emplacement();
		for(Case c : cases) {
			if(c.isPleine()==false) { //si la case n'est pas pleine
				emp.addCase(c);
			}else {
				if(emp.size()>=2) { //si on a au moins 2 cases non pleines
					places.add(emp);
				}
				emp = new Emplacement();
			}
		}
		if(emp.size()>=2) {
			places.add(emp);
		}
	}
	
	/* tme2 */
	
	public GrillePlaces	fixer(int m, String soluce) {
		Grille new_grille = grille.copy();
		GrillePlaces new_gp = new GrillePlaces(new_grille);
		Emplacement emp = new_gp.getPlaces().get(m);
		int i=0;
		for(Case c : emp.getCases()) {
			c.setChar(soluce.charAt(i));
			i++;
		}
		
		return new GrillePlaces(new_grille);
	}
	
	/**
	 * @param emplacement donné
	 * @return vrai s'il a au moins une case vide, faux sinon
	 */
	public boolean hasCaseVide(int emp) {
		return places.get(emp).hasCaseVide();
	}
	
}

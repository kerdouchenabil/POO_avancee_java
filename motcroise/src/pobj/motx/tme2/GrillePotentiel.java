package pobj.motx.tme2;
import pobj.motx.tme1.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Représente les mots existants dans dictionnaire pouvant etre solution de la grille donnée après passage par plusieurs filtres
 */
/**
 * @author clinton
 *
 */
public class GrillePotentiel {
	
	/**
	 * grille representant les emplacements des mots
	 */
	private GrillePlaces grille;
	/**
	 * dictionnaire contenant tous les mots
	 */
	private Dictionnaire dic;
	/**
	 * liste de dictionnaires contenant les mots potentiels (solutions possibles)
	 */
	private List<Dictionnaire> motsPot;
	/**
	 * liste des contraintes suite aux croisements des mots
	 */
	private List<IContrainte> contraintes;
	
	/**
	 * Constructeur
	 * @param emplacements des mots
	 * @param dictionnaire Complet
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille = grille ;
		this.dic = dicoComplet;
		motsPot = new ArrayList<Dictionnaire>();
		this.contraintes = new ArrayList<>();
		
		List<Emplacement> e =grille.getPlaces();
		for(Emplacement e1 : e) {
			Dictionnaire d = this.dic.copy();
			
			//filtre par longeur des mots
			d.filtreLongueur(e1.size());
			
			//filtre par lettres
			int i=0; //indice de la lettre dans le mot
			for(Case c : e1.getCases()) {
				if(c.isVide()==false) { //la case contient une lettre deja placee
					d.filtreParLettre(c.getChar(), i);
				}
				i++;
			}
			
			motsPot.add(d);
		}
		
		//detection des contraintes
		IContrainte c;
		for(int m1=0; m1<grille.getNbHorizontal(); m1++){
			List<Case> motsHoriz = grille.getPlaces().get(m1).getCases();
			
			for (int m2=grille.getNbHorizontal(); m2<grille.getPlaces().size(); m2++){
				List<Case> motsVerti = grille.getPlaces().get(m2).getCases();
				
				for(int c1=0; c1<motsHoriz.size(); c1++){
					if(motsHoriz.get(c1).isVide()){
						
						for(int c2=0; c2<motsVerti.size(); c2++){
							if(motsHoriz.get(c1)== motsVerti.get(c2)){
								c = new CroixContrainte(m1,c1,m2,c2);
								
								if(!contraintes.contains(c))
									contraintes.add(c);
							}
						}
					}
				}
			}
		}
		
		//propagation des contraintes
		if(this.propage()) {
			//System.out.println("Grille REALISABLE"); //print facultatif
		}else
		{
			//System.out.println("Grille NON REALISABLE !"); //print facultatif
		};
		
	}
	
	
	/**
	 * @return liste des mots potentiels
	 */
	public List<Dictionnaire> getMotsPot(){
		return motsPot;
	} 
	
	/**
	 * @return vrai si la grille est irréalisable (aucun mot restant)
	 */
	public boolean isDead() {
		for(Dictionnaire e : motsPot) {
			if(e.size()==0) {
				return true ;
			}
		}
		return false ; 
	}
	
	
	/**
	 * @param numero de l'emplacement du mot a placer
	 * @param mot a fixer
	 * @return nouvelle GrillePotentiel apres placement du mot
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		GrillePlaces new_gplaces = grille.fixer(m, soluce);
		return new GrillePotentiel(new_gplaces, dic);
	}

	/**
	 * @return la liste des contraintes
	 */
	public List<IContrainte> getContraintes() {
		return this.contraintes;
	}
	
	
	/**
	 * propage les contraintes (filtre la grille au maximum possible)
	 * @return vrai si la grille est réalisable, faux sinon
	 */
	private boolean propage() {
		while (true) {
			int cpt = 0 ; //nombre de mots filtrés
			for(IContrainte c : this.contraintes) {
				cpt+=c.reduce(this);
			}
			if(this.isDead()) {return false;}
			if(cpt == 0) {return true ;}
		}
	}
	
	/*tme3*/
	/**
	 * @param emplacement donné
	 * @return vrai s'il contient au moins une case vide, faux sinon
	 */
	public boolean hasCaseVide(int emp) {
		return grille.hasCaseVide(emp);
	}
	
	/**
	 * @return grillePlaces
	 */
	public GrillePlaces getGrillePlaces() {
		return this.grille;
	}
	
	/**
	 * @return liste des mots (String) de tous les dictionnaires 
	 * pas besoin pour le moment !
	 */
	/*
	public List<String> getListtMots(){
		List<String> list = new ArrayList<String>();
		for(Dictionnaire d : motsPot) {
			list.addAll(d.getMots());
		}
		return list;
	}
	*/
	
}


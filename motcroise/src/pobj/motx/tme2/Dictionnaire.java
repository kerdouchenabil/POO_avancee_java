package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public List<String> getMots(){return this.mots;}
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	
	/**
	 * representation du Dictionnaire
	 */
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
	/**
	 * @param chemin du fichier
	 * @return Dictionnaire contenant les mots du fichier
	 */
	public static Dictionnaire loadDictionnaire (String path) {
		Dictionnaire d = new Dictionnaire();
		try(BufferedReader br =new BufferedReader(new FileReader(path))) {
			for(String line = br.readLine() ; line !=null; line = br.readLine() ) {
				d.add(line);
				}
			}catch(IOException e) {
				// Problème d’accès au fichier.
				e.printStackTrace();
				}
			
		return d;
	
	}
	
	/**
	 * filtre les mots du dictionnaire qui ne contiennent pas la lettre c à la position i
	 * @param lettre (caractere)
	 * @param index (position de la lettre dans le mot)
	 * @return nombre de mots filtrés
	 */
	public int filtreParLettre(char c,int i) {
		
		List<String> cible = new ArrayList<>();
		int cpt=0; //nombre de mots supprimés
		for (String mot : mots) {
			if (mot.charAt(i) == c )
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	/**
	 * filtre les mots qui n'ont pas leur i'ème lettre dans l'ensembleLettre donné
	 * @param ensembleLettre
	 * @param indice
	 * @return nombre de mots filtrés
	 */
	public int filtreIndice(EnsembleLettre e, int i) {
		List<String> new_l = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if(e.contains(mot.charAt(i))) {
				new_l.add(mot);
			}else {
				cpt++; //nombre de mots supprimés ++
			}
		}
		
		this.mots = new_l; //on filtre notre liste
		return cpt;
	}
	
	/**
	 * retourne l'ensembleLettre contenant les lettres pouvant etre placées à la position donnée
	 * @param indice de la lettre
	 * @return EnsembleLettre
	 */
	public EnsembleLettre getEnsembleLettre(int i) {
		EnsembleLettre e= new EnsembleLettre();
		for(String s : this.mots) {
			try {
				e.add(s.charAt(i));
			}
			catch(ArrayIndexOutOfBoundsException ex){
				//ne rien faire
			}
		}
		return e;
	}
	
}

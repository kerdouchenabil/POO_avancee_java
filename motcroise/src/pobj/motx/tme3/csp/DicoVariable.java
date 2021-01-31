package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable{
	
	int index;
	GrillePotentiel gp;
	
	/**
	 * Constructeur
	 * @param indice du mot
	 * @param mots potentiels
	 */
	public DicoVariable (int index, GrillePotentiel gp) {
		this.index = index;
		this.gp = gp;
	}
	
	public String toString() {
		String s = "DicoVariable index="+index+" ; mots (Domaine):\n"+getDomain().toString();
		return s;
	}

	@Override
	public List<String> getDomain() {
		return gp.getMotsPot().get(index).getMots();
	}

	public int getIndex() {
		return index;
	}
	
}

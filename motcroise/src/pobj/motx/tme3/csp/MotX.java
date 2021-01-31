package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP{
	
	List<IVariable> dicoVariables; //List<DicoVariable>  ??
	GrillePotentiel gp;
	
	public MotX(GrillePotentiel gp) {
		dicoVariables = new ArrayList<IVariable>();
		this.gp = gp;
		//une dicoVariable pour chaque Emplacement de la gp qui comporte au moins une case vide
		for(int i=0;i<gp.getGrillePlaces().size();i++) {
			if(gp.getGrillePlaces().getPlaces().get(i).hasCaseVide()) {
				dicoVariables.add(new DicoVariable(i,gp));
			}
		}
	}
	
	public GrillePotentiel getGp() {
		return this.gp;
	}
	
	@Override
	public List<IVariable> getVars() {
		return dicoVariables;
	}

	/**
	 * retourne vrai si le problème est encore satisfiable
	 */
	@Override
	public boolean isConsistent() {
		return ( ! gp.isDead()); // a verifier !
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		//instanceof puis cast de vi
		if( ! (vi instanceof DicoVariable)) {
			return new MotX(gp); // a verifier !!
		}
		
		DicoVariable dv = (DicoVariable) vi;
		GrillePotentiel new_gp = gp.fixer(dv.getIndex(), val); //grille apres placement du mot
		MotX mx = new MotX(new_gp);
		return mx;
	}
	
	

}

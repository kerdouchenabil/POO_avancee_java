package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval implements IVisitor<Integer> {
	private Map<String, Integer> m ;
	
	public VisitorEvalVar(Map<String, Integer> m) {
		super();
		this.m = m ; 
	}
	
	@Override
	public Integer visit(Var v) {
		return m.getOrDefault(v.getName(), null);
	}
}

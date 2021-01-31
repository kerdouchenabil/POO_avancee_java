package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {

	@Override
	public Integer visit(Constant c) {
		return c.getValue();
	}

	@Override
	public Integer visit(Add e) throws UnsupportedOperationException {
		Integer a1 = e.getLeft().accept(this); 
		Integer a2 = e.getRight().accept(this); 
		Integer res = a1 + a2;
		return res;
	}

	@Override
	public Integer visit(Mult e) throws UnsupportedOperationException {
		Integer a1 = e.getLeft().accept(this); 
		Integer a2 = e.getRight().accept(this); 
		Integer res = a1 * a2;
		return res;
	}

	@Override
	public Integer visit(Var v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Var eval()");
	}
	
}

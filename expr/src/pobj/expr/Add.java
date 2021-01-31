package pobj.expr;

public class Add extends BinOp implements Expression {

	public Add(Expression l, Expression r) {
		super(l, r);
	}

	@Override
	public String toString() {
		return "( "+getLeft()+" + "+getRight()+" )";
	}
	
	// getters hérités de la classe mere 
	
	@Override
	public int eval() throws UnsupportedOperationException {
		int res = getLeft().eval() + getRight().eval();
		return res;
	}
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}

}

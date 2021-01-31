package pobj.expr;

import pobj.expr.Add;
import pobj.expr.Constant;
import pobj.expr.Expression;
import pobj.expr.IVisitor;
import pobj.expr.Mult;
import pobj.expr.Var;

public class VisitorDerive implements IVisitor<Expression>{
	private Var v ;
	public VisitorDerive(Var v ) {
		this.v=v;
	}
	@Override
	public Expression visit(Constant c) {
		return new Constant();
	}

	@Override
	public Expression visit(Add e) {
		
		return new Add(e.getLeft().accept(this),e.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		return new Add( (new Mult(e.getLeft(), e.getRight().accept(this))), (new Mult(e.getLeft().accept(this), e.getRight())) );
	}

	@Override
	public Expression visit(Var v) {
		if(this.v.getName()==v.getName()) {
			return new Constant(1);
		}
		return new Constant();
	}
}

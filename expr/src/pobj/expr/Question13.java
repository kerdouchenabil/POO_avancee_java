package pobj.expr;
import pobj.expr.Expression;
import pobj.expr.IVisitor;

public class Question13 {
	public static <T> T compose(IVisitor<T> f, IVisitor<Expression> g, Expression e) { 
		Expression a=e.accept(g);
		return a.accept(f);
		
		} 
}

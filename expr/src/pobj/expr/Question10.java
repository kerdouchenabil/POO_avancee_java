package pobj.expr;
public class Question10 {
	// retourne vrai si e est un arbre d’expression constant 
	public static boolean isConstant(Expression e) { 
		return e.accept(new VisitorConstant() );
		
	} 
	// retourne la valeur d’une expression constante 
	// signale une exception si l’expression n’est pas constante 
	public static int evalConstantExpression (Expression e) throws UnsupportedOperationException{ 
		if(!Question10.isConstant(e)) {
			throw new UnsupportedOperationException("Var eval()");
		}
		return e.eval();
	} 
}

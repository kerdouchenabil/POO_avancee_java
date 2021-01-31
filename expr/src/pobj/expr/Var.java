package pobj.expr;

public class Var implements Expression {
	
	private final String name;
	
	public Var(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean equals(Object o) {
		if(o==null) return false;
		if(o==this) return true;
		if( ! (o instanceof Var)) return false;
		Var other = (Var) o;
		if(other.name==this.name) return true;
		return false;
	}

	@Override
	public int eval() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Var eval()");
	}
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
	
}

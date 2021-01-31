package pobj.expr;

public class Constant implements Expression {
	
	private int val;
	
	public Constant() {
		val = 0;
	}
	
	public Constant (int i) {
		val = i;
	}
	
	public int getValue() {
		return val;
	}
	
	public boolean equals(Object o) {
		if(o==null) return false;
		if(o==this) return true;
		if( ! (o instanceof Constant)) return false;
		Constant other = (Constant) o;
		if(other.val==this.val) return true;
		return false;
	}
	
	@Override
	public String toString () {
		return ""+val;
	}

	@Override
	public int eval() {
		return val;
	}

	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visit(this);
	}
	
}

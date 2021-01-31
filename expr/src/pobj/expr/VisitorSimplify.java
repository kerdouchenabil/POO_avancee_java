package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

    @Override
    public Expression visit(Constant c) {
        return c;
    }

    @Override
    public Expression visit(Add e) {
        Expression l = e.getLeft().accept(this);
        Expression r = e.getRight().accept(this);
        Constant zero = new Constant();
        if(l.equals(zero)) {
            return r;
        }
        if(r.equals(zero)) {
            return l ;
        }
        return e;
    }

    @Override
    public Expression visit(Mult e) {
        Expression l = e.getLeft().accept(this);
        Expression r = e.getRight().accept(this);
        Constant zero = new Constant();
        Constant un = new Constant(1);
        if(l.equals(zero) || r.equals(zero)) {
            return zero;
        }
        if(l.equals(un)) {
            return r;
        }
        if(r.equals(un)) {
            return l ;
        }
        return e;


    }

    @Override
    public Expression visit(Var v) {
        return v;
    }

}
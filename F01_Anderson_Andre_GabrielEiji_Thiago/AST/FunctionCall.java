package AST;

import java.util.List;

class FunctionCall extends ExpressionPrimary implements Printable{
    
    private Identifier Id;
    private List<Expression> Expr;

    FunctionCall(Identifier Id, List<Expression> Expr) {
        this.Id = Id;
        this.Expr = Expr;
    }

    @Override
    public void genC(PW pw) {

    }
}
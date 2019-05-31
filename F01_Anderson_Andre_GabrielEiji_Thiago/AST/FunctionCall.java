/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
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
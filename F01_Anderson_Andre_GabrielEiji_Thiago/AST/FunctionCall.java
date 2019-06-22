/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

class FunctionCall extends ExpressionPrimary implements Printable{
    
    private Identifier id;
    private List<Expression> expressions;

    FunctionCall(Identifier Id, List<Expression> expressions) {
        this.id = Id;
        this.expressions = expressions;
    }

    @Override
    public void genC(PW pw) {
        id.genC(pw);
        if(expressions == null || expressions.size() == 0) {
            return;
        }
        expressions.get(0).genC(pw);
        expressions.stream().skip(1).forEach(expression -> {
            pw.print(", ");
            expression.genC(pw);
        });
    }
    
    @Override
    public Type getType() {
    	return this.id.getType();
    }
}
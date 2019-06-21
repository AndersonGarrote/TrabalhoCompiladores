/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.ArrayList;
import java.util.List;

public class ExpressionFunctionCall extends ExpressionPrimary implements Printable {

    private Identifier identifier;
    private List<Expression> expressions;

    public ExpressionFunctionCall(Identifier identifier, Expression expression) {

        expressions = new ArrayList<Expression>();

        this.identifier = identifier;
        this.expressions.add(expression);

    }

    public void addExpression(Expression expression) {
        expressions.add(expression);
    }

    public int getSize() {
        return this.expressions.size();
    }

    public void genC(PW pw) {

    }
    @Overrride
    Type getType() {
    	return this.identifier.getType();
    }
}
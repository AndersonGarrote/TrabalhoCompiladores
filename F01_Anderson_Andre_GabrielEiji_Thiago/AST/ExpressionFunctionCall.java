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

    private Function function;
    private List<Expression> expressions;
    
    public ExpressionFunctionCall(Function function) {
    	expressions = new ArrayList<Expression>();
        this.function = function;
    }
    
    public ExpressionFunctionCall(Function function, Expression expression) {

        expressions = new ArrayList<Expression>();

        this.function = function;
        this.expressions.add(expression);

    }

    public void addExpression(Expression expression) {
        expressions.add(expression);
    }

    public void genC(PW pw) {

    }

    @Override
    public Type getType() {
        return function.getType();
    }
    
    @Override
    public boolean isFunctionWithReturn() {
    	return function.getType() != null;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

}
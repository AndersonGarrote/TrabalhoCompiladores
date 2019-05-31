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

    public void genC(PW pw) {

    }

}
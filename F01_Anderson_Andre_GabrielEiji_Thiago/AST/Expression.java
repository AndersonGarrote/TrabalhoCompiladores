package AST;

import java.util.List;

public class Expression implements Printable {

    private List<ExpressionAnd> expressionEnds;

    Expression(List<ExpressionAnd> expressionEnds) {
        this.expressionEnds = expressionEnds;
    }

    @Override
    public void genC(PW pw) {

    }

}
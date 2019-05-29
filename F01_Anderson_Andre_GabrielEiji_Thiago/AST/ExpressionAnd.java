package AST;

import java.util.List;

public class ExpressionAnd implements Printable {

    private List<ExpressionRelational> expressionOrs;

    ExpressionAnd(List<ExpressionRelational> expressionOrs) {
        this.expressionOrs = expressionOrs;
    }

    @Override
    public void genC(PW pw) {

    }

}
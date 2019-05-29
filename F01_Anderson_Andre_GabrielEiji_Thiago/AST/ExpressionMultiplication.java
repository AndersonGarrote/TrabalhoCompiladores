package AST;

import java.util.List;

class ExpressionMultiplication implements Printable {

    private List<String> operator;
    private List<ExpressionUnary> expressionUnary;

    ExpressionMultiplication(List<String> operator, List<ExpressionUnary> expressionUnary) {
        this.operator = operator;
        this.expressionUnary = expressionUnary;
    }

    @Override
    public void genC(PW pw) {

    }

}
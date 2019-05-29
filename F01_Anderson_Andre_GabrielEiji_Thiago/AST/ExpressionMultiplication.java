package AST;

import java.util.List;

class ExpressionMultiplication implements Printable {

    private List<String> operator;
    private List<ExpressionUnary> exprUnary;

    ExpressionMultiplication(List<String> operator, List<ExpressionUnary> exprUnary) {
        this.operator = operator;
        this.exprUnary = exprUnary;
    }

    @Override
    public void genC(PW pw) {

    }

}
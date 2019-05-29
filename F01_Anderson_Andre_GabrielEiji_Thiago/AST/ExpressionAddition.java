package AST;

import java.util.List;

class ExpressionAddition implements Printable {

    List<String> operator;
    List<ExpressionMultiplication> exprMult;

    ExpressionAddition(List<String> operator, List<ExpressionMultiplication> exprMult) {
        this.operator = operator;
        this.exprMult = exprMult;
    }

    @Override
    public void genC(PW pw) {

    }

}
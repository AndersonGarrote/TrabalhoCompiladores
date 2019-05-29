package AST;

import java.util.List;

class ExpressionAddition implements Printable {

    List<String> operator;
    List<ExpressionMultiplication> expressionMultiplication;

    ExpressionAddition(List<String> operator, List<ExpressionMultiplication> expressionMultiplication) {
        this.operator = operator;
        this.expressionMultiplication = expressionMultiplication;
    }

    @Override
    public void genC(PW pw) {

    }

}
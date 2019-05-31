package AST;

import java.util.ArrayList;
import java.util.List;

import Lexer.Symbol;
import javafx.util.Pair;

public class ExpressionAddition implements Printable {

    List<Pair<Symbol, ExpressionMultiplication>> operatorExpressionMultiplicationPairs;

    public ExpressionAddition(ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs = new ArrayList<Pair<Symbol, ExpressionMultiplication>>();
        this.operatorExpressionMultiplicationPairs.add(new Pair<Symbol, ExpressionMultiplication>(Symbol.PLUS, expressionMultiplication));
    }

    public void addOperatorExpressionMultiplication(Symbol operator, ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs.add(new Pair<Symbol, ExpressionMultiplication>(operator, expressionMultiplication));
    }

    @Override
    public void genC(PW pw) {

    }

}
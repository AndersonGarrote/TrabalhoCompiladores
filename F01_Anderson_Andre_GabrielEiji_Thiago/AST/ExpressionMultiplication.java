package AST;

import java.util.ArrayList;
import java.util.List;

import Lexer.Symbol;
import javafx.util.Pair;

public class ExpressionMultiplication implements Printable {

    List<Pair<Symbol, ExpressionUnary>> operatorExpressionUnaryPairs;

    public ExpressionMultiplication(ExpressionUnary expressionUnary) {
        this.operatorExpressionUnaryPairs = new ArrayList<Pair<Symbol, ExpressionUnary>>();
        this.operatorExpressionUnaryPairs.add(new Pair<Symbol, ExpressionUnary>(Symbol.TIMES, expressionUnary));
    }

    public void addOperatorExpressionUnary(Symbol operator, ExpressionUnary expressionUnary) {
        this.operatorExpressionUnaryPairs.add(new Pair<Symbol, ExpressionUnary>(operator, expressionUnary));
    }

    @Override
    public void genC(PW pw) {

    }

}
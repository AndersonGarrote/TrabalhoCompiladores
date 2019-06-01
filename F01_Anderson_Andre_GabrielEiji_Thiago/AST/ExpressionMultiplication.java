/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
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
        operatorExpressionUnaryPairs.get(0).getValue().genC(pw);

        operatorExpressionUnaryPairs.stream().skip(1).forEach(pair -> {
            pw.print(" " + pair.getKey() + " ");
            pair.getValue().genC(pw);
        });
    }

}
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Lexer.Symbol;
import javafx.util.Pair;

public class ExpressionAddition implements Printable {

    List<Pair<Symbol, ExpressionMultiplication>> operatorExpressionMultiplicationPairs;

    public ExpressionAddition(ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs = new ArrayList<Pair<Symbol, ExpressionMultiplication>>();
        this.operatorExpressionMultiplicationPairs
                .add(new Pair<Symbol, ExpressionMultiplication>(Symbol.PLUS, expressionMultiplication));
    }

    public void addOperatorExpressionMultiplication(Symbol operator,
            ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs
                .add(new Pair<Symbol, ExpressionMultiplication>(operator, expressionMultiplication));
    }

    @Override
    public void genC(PW pw) {

    }

}
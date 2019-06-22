/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import Lexer.Symbol;

public class ExpressionMultiplication implements Printable {

    List<AbstractMap.SimpleEntry<Symbol, ExpressionUnary>> operatorExpressionUnaryPairs;

    public ExpressionMultiplication(ExpressionUnary expressionUnary) {
        this.operatorExpressionUnaryPairs = new ArrayList<AbstractMap.SimpleEntry<Symbol, ExpressionUnary>>();
        this.operatorExpressionUnaryPairs.add(new AbstractMap.SimpleEntry<Symbol, ExpressionUnary>(Symbol.TIMES, expressionUnary));
    }

    public void addOperatorExpressionUnary(Symbol operator, ExpressionUnary expressionUnary) {
        this.operatorExpressionUnaryPairs.add(new AbstractMap.SimpleEntry<Symbol, ExpressionUnary>(operator, expressionUnary));
    }

    @Override
    public void genC(PW pw) {
        operatorExpressionUnaryPairs.get(0).getValue().genC(pw);

        operatorExpressionUnaryPairs.stream().skip(1).forEach(pair -> {
            pw.print(" " + pair.getKey() + " ");
            pair.getValue().genC(pw);
        });
    }
    
    
    public Type getType(){
    	return this.operatorExpressionUnaryPairs.get(0).getValue().getType();
    }

}
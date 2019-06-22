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

public class ExpressionAddition implements Printable {

    List<AbstractMap.SimpleEntry<Symbol, ExpressionMultiplication>> operatorExpressionMultiplicationPairs;

    public ExpressionAddition(ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs = new ArrayList<AbstractMap.SimpleEntry<Symbol, ExpressionMultiplication>>();
        this.operatorExpressionMultiplicationPairs
                .add(new AbstractMap.SimpleEntry<Symbol, ExpressionMultiplication>(Symbol.PLUS, expressionMultiplication));
    }

    public void addOperatorExpressionMultiplication(Symbol operator,
            ExpressionMultiplication expressionMultiplication) {
        this.operatorExpressionMultiplicationPairs
                .add(new AbstractMap.SimpleEntry<Symbol, ExpressionMultiplication>(operator, expressionMultiplication));
    }

    @Override
    public void genC(PW pw) {

        operatorExpressionMultiplicationPairs.get(0).getValue().genC(pw);

        operatorExpressionMultiplicationPairs.stream().skip(1).forEach(pair -> {
            pw.print(" " + pair.getKey() + " ");
            pair.getValue().genC(pw);
        });
        
    }
    
    public Type getType(){
    	return this.operatorExpressionMultiplicationPairs.get(0).getValue().getType();
    }
    
    public boolean isIdentifier() {
    	return this.operatorExpressionMultiplicationPairs.size() == 1
    			&& this.operatorExpressionMultiplicationPairs.get(0).getValue().isIdentifier();
    }

}
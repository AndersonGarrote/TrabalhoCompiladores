/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import Lexer.Symbol;

public class ExpressionUnary implements Printable {

    private Symbol operator;
    private ExpressionPrimary expressionPrimary;

    public ExpressionUnary(Symbol operator, ExpressionPrimary expressionPrimary) {
        this.operator = operator;
        this.expressionPrimary = expressionPrimary;
    }
    
    @Override
    public void genC(PW pw) {

    }

}
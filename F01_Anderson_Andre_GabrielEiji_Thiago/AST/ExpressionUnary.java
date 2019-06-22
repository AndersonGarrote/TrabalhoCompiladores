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

    public ExpressionUnary(ExpressionPrimary expressionPrimary) {
        this.operator = null;
        this.expressionPrimary = expressionPrimary;
    }

    public ExpressionUnary(Symbol operator, ExpressionPrimary expressionPrimary) {
        this.operator = operator;
        this.expressionPrimary = expressionPrimary;
    }
    
    @Override
    public void genC(PW pw) {
        if(operator != null) {
            pw.print(operator.toString());
        }
        expressionPrimary.genC(pw);
    }
    
    public Type getType(){
    	return this.expressionPrimary.getType();
    }
    
    public boolean isIdentifier() {
    	return this.expressionPrimary != null && this.expressionPrimary.isIdentifier();
    }
    
    public boolean isFunctionWithReturn() {
    	return this.expressionPrimary.isFunctionWithReturn();
    }

}
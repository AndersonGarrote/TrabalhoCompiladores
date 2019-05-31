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
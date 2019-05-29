package AST;

import java.util.List;

abstract public class Expression implements Printable {
    private List<ExpressionAnd> exprAnd;
    
    Expression(List<ExpressionAnd> exprAnd) {
        this.exprAnd = exprAnd;
    }
}
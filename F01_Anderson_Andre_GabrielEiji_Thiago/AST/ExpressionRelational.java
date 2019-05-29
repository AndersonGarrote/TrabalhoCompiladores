package AST;

import java.util.List;

public class ExpressionRelational implements Printable {

    private ExpressionAddition leftExpressionAddition;
    private RelationalOperator relationalOperator;
    private ExpressionAddition rightExpressionAddition;

    ExpressionRelational(ExpressionAddition leftExpressionAddition, RelationalOperator relationalOperator, ExpressionAddition rightExpressionAddition) {
        this.leftExpressionAddition = leftExpressionAddition;
        this.relationalOperator = relationalOperator;
        this.rightExpressionAddition = rightExpressionAddition;
    }

    @Override
    public void genC(PW pw) {

    }

}
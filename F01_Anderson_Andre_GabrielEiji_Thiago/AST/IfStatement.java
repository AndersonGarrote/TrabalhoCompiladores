package AST;

import java.util.List;

public class IfStatement extends Statement {

    private Expression expression;
    private StatementList statementsTrue;
    private StatementList statementFalse;

    public IfStatement(Expression expression, StatementList statementsTrue, StatementList statementsFalse) {
        this.expression = expression;
        this.statementsTrue = statementsTrue;
        this.statementFalse = statementsFalse;
    }

    @Override
    public void genC(PW pw) {

    }

}
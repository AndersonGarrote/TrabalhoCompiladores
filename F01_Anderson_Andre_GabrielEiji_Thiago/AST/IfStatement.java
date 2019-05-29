package AST;

import java.util.List;

public class IfStatement extends Statement {

    private Expression expression;
    private List<Statement> statementsTrue;
    private List<Statement> statementFalse;

    public IfStatement(Expression expression, List<Statement> statementsTrue, List<Statement> statementsFalse) {
        this.expression = expression;
        this.statementsTrue = statementsTrue;
        this.statementFalse = statementsFalse;
    }

    @Override
    public void genC(PW pw) {

    }

}
package AST;

import java.util.List;

public class WhileStatement extends Statement {

    private Expression expression;
    private StatementList statements;

    public WhileStatement(Expression expression, StatementList statements) {
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {

    }

}
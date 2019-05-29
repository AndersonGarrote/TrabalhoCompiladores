package AST;

import java.util.List;

public class WhileStatement extends Statement {

    private Expression expression;
    private List<Statement> statements;

    public WhileStatement(Expression expression, List<Statement> statements) {
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {

    }

}
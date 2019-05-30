package AST;

public class IfStatement extends Statement {

    private Expression expression;
    private StatementList statementsTrue;
    private StatementList statementsFalse;

    public IfStatement(Expression expression, StatementList statementsTrue, StatementList statementsFalse) {
        this.expression = expression;
        this.statementsTrue = statementsTrue;
        this.statementsFalse = statementsFalse;
    }

    @Override
    public void genC(PW pw) {
        pw.print("if (");
        expression.genC(pw);
        pw.print(") ");
        pw.breakLine();
        statementsTrue.genC(pw);
        pw.print(" else ");
        statementsFalse.genC(pw);
        pw.breakLine();
    }

}
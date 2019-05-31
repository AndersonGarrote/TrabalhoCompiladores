/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

public class WhileStatement extends Statement {

    private Expression expression;
    private StatementList statements;

    public WhileStatement(Expression expression, StatementList statements) {
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {
        pw.print("while (");
        expression.genC(pw);
        pw.print(") ");
        pw.breakLine();
        statements.genC(pw);
        pw.breakLine();
    }

}
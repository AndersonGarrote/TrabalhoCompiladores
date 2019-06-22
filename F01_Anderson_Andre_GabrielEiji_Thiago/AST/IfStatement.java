/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

public class IfStatement extends Statement {

    private Expression expression;
    private List<Statement> statementsTrue;
    private List<Statement> statementsFalse;

    public IfStatement(Expression expression, List<Statement> statementsTrue, List<Statement> statementsFalse) {
        this.expression = expression;
        this.statementsTrue = statementsTrue;
        this.statementsFalse = statementsFalse;
    }

    @Override
    public void genC(PW pw) {

    }

}
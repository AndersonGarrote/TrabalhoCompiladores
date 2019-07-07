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
		pw.print("if (");

		this.expression.genC(pw);

		pw.print(") {");

		pw.add();

		pw.breakLine();

		if (statementsTrue.size() > 0) {
			statementsTrue.get(0).genC(pw);
		}

		statementsTrue.stream().skip(1).forEach(statement -> {
			pw.breakLine();
			statement.genC(pw);
		});

		pw.breakLine(true);
		pw.print("} ");
		pw.sub();

		if (this.statementsFalse != null) {

			pw.print("else {");

			pw.add();

			pw.breakLine();

			if (statementsFalse.size() > 0) {
				statementsFalse.get(0).genC(pw);
			}

			statementsFalse.stream().skip(1).forEach(statement -> {
				pw.breakLine();
				statement.genC(pw);
			});

			pw.breakLine(true);
			pw.print("}");
			pw.sub();

		}

	}

}
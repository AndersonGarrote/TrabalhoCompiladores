/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

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

		pw.print("while (");

		this.expression.genC(pw);

		pw.print(") {");
		pw.add();
		pw.breakLine();

		if(statements != null && statements.size() > 0) {
			statements.get(0).genC(pw);
		}

		statements.stream().skip(1).forEach(statement -> {
			pw.breakLine();
			statement.genC(pw);
		});

		pw.breakLine(true);
		pw.print("} ");
		pw.sub();
	}

}
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

import java.util.ArrayList;
import java.util.List;

public class StatementList implements Printable {

    private List<Statement> statements;

    public StatementList() {
        statements = new ArrayList<Statement>();
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void genC(PW pw) {
        pw.print("{");
        pw.breakLine();
        statements.stream().forEach(statement -> {
            statement.genC(pw);
        });
        pw.breakLine();
        pw.print("}");
    }

}
package AST;

import java.util.List;

public class StatementList implements Printable {

    private List<Statement> statements;

    public StatementList(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {

    }

    

}
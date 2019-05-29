package AST;

import java.util.List;

public class Function implements Printable {

    private List<Param> params;
    private Type type;
    private List<Statement> statements;

    public Function(List<Param> params, Type type, List<Statement> statements) {
        this.params = params;
        this.type = type;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {

    }

}
package AST;

import java.util.List;

public class Function implements Printable {

    private ParameterList parameters;
    private Type type;
    private StatementList statements;

    public Function(ParameterList parameters, Type type, StatementList statements) {
        this.parameters = parameters;
        this.type = type;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {

    }

}
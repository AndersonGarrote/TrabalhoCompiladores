package AST;

public class Function implements Printable {

    private Identifier identifier;
    private ParameterList parameters;
    private Type type;
    private StatementList statements;

    public Function(Identifier identifier, ParameterList parameters, Type type, StatementList statements) {
        this.identifier = identifier;
        this.parameters = parameters;
        this.type = type;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {
        pw.print("function ");
        this.identifier.genC(pw);
    }

}
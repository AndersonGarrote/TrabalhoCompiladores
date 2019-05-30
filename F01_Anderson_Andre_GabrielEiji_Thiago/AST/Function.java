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

        // type identifier(parameters) statements

        if (type != null) {
            type.genC(pw);
        } else {
            pw.print("void");
        }

        pw.print(" ");

        identifier.genC(pw);

        pw.print("(");
        if (parameters != null) {
            parameters.genC(pw);
        }
        pw.print(") ");

        statements.genC(pw);

        pw.breakLine();

    }

}
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class Function implements Printable {

    private Identifier identifier;
    private ParameterList parameters;
    private Type type;
    private StatementList statements;

    public Function() {
        
    }

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
        parameters.genC(pw);
        pw.print(") ");

        statements.genC(pw);

        pw.breakLine();

    }

}
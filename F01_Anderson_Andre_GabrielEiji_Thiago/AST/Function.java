/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

public class Function extends Identifiable implements Printable {

    private List<Parameter> parameters;
    private Type type;
    private List<Statement> statements;

    public Function(Identifier identifier) {
        this.setIdentifier(identifier);
    }

    public Function(Identifier identifier, List<Parameter> parameters, Type type, List<Statement> statements) {
        this.setIdentifier(identifier);
        this.parameters = parameters;
        this.type = type;
        this.statements = statements;
    }

    @Override
    public void genC(PW pw) {
    }

    @Override
    public Type getType() {
        return type;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

}
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;
import java.util.ArrayList;

public class Function extends Identifiable implements Printable {

    private List<Parameter> parameters;
    private Type type;
    private List<Statement> statements;

    public static ReadIntFunction readIntFunction = new ReadIntFunction();
    public static ReadStringFunction readStringFunction = new ReadStringFunction();
    public static WriteFunction writeFunction = new WriteFunction();
    public static WritelnFunction writelnFunction = new WritelnFunction();

    public Function(Identifier identifier) {
        this.setIdentifier(identifier);
        this.parameters = new ArrayList<>();
        this.statements = new ArrayList<>();
    }

    public Function(Identifier identifier, List<Parameter> parameters, Type type, List<Statement> statements) {
        this.setIdentifier(identifier);
        if (parameters == null) {
            this.parameters = new ArrayList<>();
        } else {
            this.parameters = parameters;
        }
        this.type = type;
        if (statements == null) {
            this.statements = new ArrayList<>();
        } else {
            this.statements = statements;
        }
    }

    public boolean validateParameters(List<Expression> parameters) {
        for(int i = 0; i < parameters.size(); i++) {
            if(parameters.get(i).getType() != this.parameters.get(i).getType()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void genC(PW pw) {
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

}
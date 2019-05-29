package AST;

public class Parameter implements Printable {

    private Identifier identifier;
    private Type type;

    public Parameter(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void genC(PW pw) {

    }

}
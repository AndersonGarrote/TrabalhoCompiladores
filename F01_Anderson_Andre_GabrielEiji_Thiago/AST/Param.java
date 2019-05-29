package AST;

public class Param implements Printable {

    private Identifier identifier;
    private Type type;

    public Param(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void genC(PW pw) {

    }

}
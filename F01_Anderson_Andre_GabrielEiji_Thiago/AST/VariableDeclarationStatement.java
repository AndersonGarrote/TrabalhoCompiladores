package AST;

public class VariableDeclarationStatement extends Statement {

    private Identifier identifier;
    private Type type;

    public VariableDeclarationStatement(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void genC(PW pw) {

    }

}
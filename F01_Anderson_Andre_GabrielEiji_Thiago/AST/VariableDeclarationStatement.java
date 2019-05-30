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
        type.genC(pw);
        pw.print(" ");
        identifier.genC(pw);
        pw.print(";");
        pw.breakLine();
    }

}
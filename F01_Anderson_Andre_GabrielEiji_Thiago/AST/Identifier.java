package AST;

public class Identifier extends ExpressionPrimary implements Printable {

    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public void genC(PW pw) {
        pw.print(name);
    }

}
package AST;

public class LiteralBoolean extends Literal implements Printable{

    private boolean value;

    LiteralBoolean(boolean value) {
        this.value = value;
    }

    public void genC(PW pw) {
        pw.out.print(this.value ? "true" : "false");
    }

}
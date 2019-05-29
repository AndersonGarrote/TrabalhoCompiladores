package AST;

public class LiteralString extends Literal implements Printable{

    private String value;

    LiteralString(String value) {
        this.value = value;
    }

    public void genC(PW pw) {
        pw.out.print("\"" + this.value + "\"");
    }

}
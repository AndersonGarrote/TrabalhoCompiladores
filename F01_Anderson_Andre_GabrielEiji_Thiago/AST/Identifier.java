package AST;

public class Identifier implements Printable {

    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public void genC(PW pw) {

    }

}
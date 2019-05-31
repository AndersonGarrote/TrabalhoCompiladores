/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
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
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class LiteralBoolean extends Literal implements Printable{

    private boolean value;

    public LiteralBoolean(boolean value) {
        this.value = value;
        this.type = Type.booleanType;
    }

    public void genC(PW pw) {
        pw.out.print(this.value ? "true" : "false");
    }

}
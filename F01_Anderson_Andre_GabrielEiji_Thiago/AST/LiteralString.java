/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class LiteralString extends Literal implements Printable{

    private String value;

    public LiteralString(String value) {
        this.value = value;
        this.type = Type.stringType;
    }

    public void genC(PW pw) {
        pw.out.print("((String) { \"" + this.value + "\" })");
    }

}
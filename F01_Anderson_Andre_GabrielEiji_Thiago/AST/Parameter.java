/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

public class Parameter implements Printable {

    private Identifier identifier;
    private Type type;

    public Parameter(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void genC(PW pw) {
        type.genC(pw);
        pw.print(" ");
        identifier.genC(pw);
    }
}
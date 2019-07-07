/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

public class Parameter extends Variable implements Printable {

    public Parameter(Identifier identifier, Type type) {
        super(identifier, type);
    }

    @Override
    public void genC(PW pw) {
        this.getType().genC(pw);
        pw.print(" ");
        this.getIdentifier().genC(pw);
    }
    
}
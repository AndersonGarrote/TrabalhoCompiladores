/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public abstract class ExpressionPrimary implements Printable {
    
    abstract public Type getType();
    
    public boolean isIdentifier() {
    	return this.getClass() == Identifier.class;
    }
}
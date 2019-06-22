/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class Variable extends Identifiable {

	private Type type;
	
	public Variable(Identifier identifier, Type type) {
		this.setIdentifier(identifier);
		this.type = type;
	}

	@Override
	public Type getType() {
		return type;
	}

}
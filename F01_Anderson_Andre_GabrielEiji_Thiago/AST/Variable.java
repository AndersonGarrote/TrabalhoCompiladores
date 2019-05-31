/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class Variable {
	
	private String name;
	private Type type;
	
	public Variable( String name, Type type ) {
		this.name = name;
		this.type = type;
	}
	public Variable( String name ) {
		this.name = name;
	}
	public void setType( Type type ) {
		this.type = type;
	}
	public String getName() { return name; }
	public Type getType() {
		return type;
	}
}
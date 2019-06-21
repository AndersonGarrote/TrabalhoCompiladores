/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class Identifier extends ExpressionPrimary implements Printable {

    private String name;
    private Type type;
    
    public Identifier(String name, Type type) {
        this.name = name;
        this.setType(type);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void genC(PW pw) {
        pw.print(name);
    }

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
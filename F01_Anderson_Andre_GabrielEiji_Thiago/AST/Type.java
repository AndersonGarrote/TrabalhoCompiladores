/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

abstract public class Type implements Printable {

	abstract public String getCName();

	private String name;

	public Type(String name) {
		this.name = name;
	}

	public static Type booleanType = new BooleanType();
	public static Type integerType = new IntegerType();
	public static Type stringType = new StringType();

	public String getName() {
		return name;
	}

	public void genC(PW pw) {
		pw.print(getCName());
	}

}
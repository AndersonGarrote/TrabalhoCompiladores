/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

abstract public class RelationalOperator implements Printable {

	abstract public String getCname();
	private String name;

	public RelationalOperator( String name ) {
		this.name = name;
	}

	public static RelationalOperator greaterOperator = new GreaterOperator();
	public static RelationalOperator greaterOrEqualOperator = new GreaterOrEqualOperator();
	public static RelationalOperator lessOperator = new LessOperator();
	public static RelationalOperator lessOrEqualOperator = new LessOrEqualOperator();
	public static RelationalOperator equalOperator = new EqualOperator();
	public static RelationalOperator notEqualOperator = new NotEqualOperator();

	public String getName() {
		return name;
	}

	@Override
	public void genC(PW pw) {
		pw.print(this.getCname());
	}
}
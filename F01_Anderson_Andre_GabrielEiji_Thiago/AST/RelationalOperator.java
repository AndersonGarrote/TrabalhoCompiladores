package AST;

abstract public class RelationalOperator {

	abstract public String getCname();
	private String name;

	public RelationalOperator( String name ) {
		this.name = name;
	}

	public static RelationalOperator greater = new GreaterOperator();
	public static RelationalOperator greaterEqual = new GreaterEqualOperator();
	public static RelationalOperator less = new LessOperator();
	public static RelationalOperator lessEqual = new LessEqualOperator();
	public static RelationalOperator equal = new EqualOperator();
	public static RelationalOperator notEqual = new NotEqualOperator();

	public String getName() {
		return name;
	}
}
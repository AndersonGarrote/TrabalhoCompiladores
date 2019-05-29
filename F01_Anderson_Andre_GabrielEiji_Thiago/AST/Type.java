package AST;

abstract public class Type implements Printable {

	abstract public String getCname();

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

<<<<<<< HEAD
	public String getCName() {
		return name;
	}
=======
>>>>>>> b00ac872d33e6f5dc0bc902670f4a1117b815e02
}
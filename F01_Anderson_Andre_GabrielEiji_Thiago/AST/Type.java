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
>>>>>>> c49a77c715dacfc4e81bf072dbf912dc8ac949b5
}
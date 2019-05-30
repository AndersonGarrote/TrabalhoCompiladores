package AST;

public class IntegerType extends Type {
	public IntegerType(String name) {
		super("integer");
	}

	@Override
	public String getCName() {
		return "int";
	}
}
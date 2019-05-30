package AST;

public class StringType extends Type {
	public StringType(String name) {
		super("string");
	}

	@Override
	public String getCName() {
		return "char*";
	}
}
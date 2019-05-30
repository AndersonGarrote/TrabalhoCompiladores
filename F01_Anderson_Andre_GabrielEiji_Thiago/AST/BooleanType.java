package AST;

public class BooleanType extends Type {
	
	public BooleanType(String name) {
		super("boolean");
	}

	@Override
	public String getCName() {
		return "char";
	}
}
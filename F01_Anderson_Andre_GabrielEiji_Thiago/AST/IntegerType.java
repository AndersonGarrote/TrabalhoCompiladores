package AST;

public class IntegerType extends Type {
	public IntegerType() {
		super("integer");
	}

	public String getCname() {
		return "int";
	}

	@Override
	public void genC(PW pw) {

	}
}
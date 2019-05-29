package AST;

public class BooleanType extends Type {
	public BooleanType() {
		super("boolean");
	}

	public String getCname() {
		return "char";
	}

	@Override
	public void genC(PW pw) {

	}
}
<<<<<<< HEAD
package AST;

public class IntegerType extends Type {
	public IntegerType() {
		super("integer");
	}
	public String getCname() {
		return "int";
	}
=======
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
>>>>>>> c49a77c715dacfc4e81bf072dbf912dc8ac949b5
}
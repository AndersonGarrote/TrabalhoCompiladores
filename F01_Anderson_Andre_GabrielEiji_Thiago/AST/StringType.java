package AST;

public class StringType extends Type {
	public StringType() {
		super("string");
	}
	public String getCname() {
		return "char";
	}
}
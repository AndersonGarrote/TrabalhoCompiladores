package AST;

abstract public class RelationOperation {

	abstract public String getCname();
	private String name;

	public RelationOperation( String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
package AST;

public abstract class Identifiable {

    private Identifier identifier;

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
        identifier.setIdentifiable(this);
    }

    public abstract Type getType();

}
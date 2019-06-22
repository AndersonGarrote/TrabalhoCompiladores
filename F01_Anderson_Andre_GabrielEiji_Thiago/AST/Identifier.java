/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class Identifier extends ExpressionPrimary implements Printable {

    private String name;
    private Identifiable identifiable;

    public Identifier(String name) {
        this.name = name;
        this.identifiable = null;
    }
    
    public Identifier(String name, Identifiable identifiable) {
        this.name = name;
        identifiable.setIdentifier(this);
        this.identifiable = identifiable;
    }

    public String getName() {
        return this.name;
    }

	public Type getIdentifiableType() {
		return identifiable.getType();
    }
    
    public Identifiable getIdentifiable() {
        return identifiable;
    }

    public void setIdentifiable(Identifiable identifiable) {
        if(identifiable.getIdentifier().getName() != this.name) {
            identifiable.setIdentifier(this);
        }
        this.identifiable = identifiable;
    }

    @Override
    public void genC(PW pw) {
        pw.print(name);
    }

}
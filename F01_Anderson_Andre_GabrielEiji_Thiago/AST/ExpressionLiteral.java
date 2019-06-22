/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class ExpressionLiteral extends ExpressionPrimary implements Printable {

    private Literal literal;

    public ExpressionLiteral(Literal literal) {
        this.literal = literal;
    }

    public void genC(PW pw) {
        this.literal.genC(pw);
    }
    
    @Override
    public Type getType(){
    	return this.literal.getType();
    }

}
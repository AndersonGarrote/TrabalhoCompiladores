package AST;

public class ExpressionLiteral extends ExpressionPrimary implements Printable{

private Literal literal;

    public ExpressionLiteral(Literal literal) {
        this.literal = literal;
    }

    public void genC(PW pw) {
        this.literal.genC(pw);
    }

}
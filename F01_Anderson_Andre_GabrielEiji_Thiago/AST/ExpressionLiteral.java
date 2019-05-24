package AST;

public class ExpressionLiteral extends Expression {

    private Literal literal;

    ExpressionLiteral(Literal literal) {
        this.literal = literal;
    }

    public void genC(PW pw) {
        this.literal.genC(pw);
    }

}
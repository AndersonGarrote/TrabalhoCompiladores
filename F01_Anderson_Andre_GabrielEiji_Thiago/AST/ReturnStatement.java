package AST;

public class ReturnStatement extends Statement {

    private Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void genC(PW pw) {

    }

}
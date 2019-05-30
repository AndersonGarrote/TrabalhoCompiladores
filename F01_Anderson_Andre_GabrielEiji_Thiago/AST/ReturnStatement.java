package AST;

public class ReturnStatement extends Statement {

    private Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void genC(PW pw) {
        pw.print("return ");
        expression.genC(pw);
        pw.print(";");
        pw.breakLine();
    }

}
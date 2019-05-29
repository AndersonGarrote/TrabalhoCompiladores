package AST;

public class AssignmentExpressionStatement extends Statement {

    private Expression leftExpression;
    private Expression rightExpression;

    public AssignmentExpressionStatement(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public void genC(PW pw) {

    }

}
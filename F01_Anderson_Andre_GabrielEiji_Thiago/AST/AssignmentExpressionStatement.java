/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class AssignmentExpressionStatement extends Statement {

    private Expression leftExpression;
    private Expression rightExpression;

    public AssignmentExpressionStatement(Expression leftExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = null;
    }

    public AssignmentExpressionStatement(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public void genC(PW pw) {
        leftExpression.genC(pw);
        if (rightExpression != null) {
            pw.print(" = ");
            rightExpression.genC(pw);
        }
        pw.print(";");
        pw.breakLine();
    }

}
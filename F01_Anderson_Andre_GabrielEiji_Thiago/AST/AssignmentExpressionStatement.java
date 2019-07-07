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

        if (rightExpression != null) {

            if (rightExpression.isFunctionWithReturn() && rightExpression.getFunction() == Function.readIntFunction) {
                readIntFunctionGenC(pw);
            } else if (rightExpression.isFunctionWithReturn()
                    && rightExpression.getFunction() == Function.readStringFunction) {
                readStringFunctionGenC(pw);
            } else {
                assignGenC(pw);
            }

        } else {
            leftExpression.genC(pw);
            pw.print(";");
        }

    }

    private void assignGenC(PW pw) {
        leftExpression.genC(pw);
        pw.print(" = ");
        rightExpression.genC(pw);
        pw.print(";");
    }

    private void readIntFunctionGenC(PW pw) {
        pw.print("scanf(\" %d\", &");
        leftExpression.genC(pw);
        pw.print(");");
    }

    private void readStringFunctionGenC(PW pw) {
        pw.print("scanf(\" %254[^\\n]s\", ");
        leftExpression.genC(pw);
        pw.print(".data);");
    }
}
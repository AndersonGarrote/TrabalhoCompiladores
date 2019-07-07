/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

public class ExpressionRelational implements Printable {

    private ExpressionAddition leftExpressionAddition;
    private RelationalOperator relationalOperator;
    private ExpressionAddition rightExpressionAddition;

    public ExpressionRelational(ExpressionAddition expressionAddition) {
        this.leftExpressionAddition = expressionAddition;
        this.relationalOperator = null;
        this.rightExpressionAddition = null;
    }

    public ExpressionRelational(ExpressionAddition leftExpressionAddition, RelationalOperator relationalOperator,
            ExpressionAddition rightExpressionAddition) {
        this.leftExpressionAddition = leftExpressionAddition;
        this.relationalOperator = relationalOperator;
        this.rightExpressionAddition = rightExpressionAddition;
    }

    @Override
    public void genC(PW pw) {

        if (relationalOperator == RelationalOperator.equalOperator) {
            if (leftExpressionAddition.getType() == Type.stringType) {
                leftExpressionAddition.genC(pw);
                pw.print(".data == ");
                rightExpressionAddition.genC(pw);
                pw.print(".data");
            }
        } else if (relationalOperator == RelationalOperator.notEqualOperator) {
            if (leftExpressionAddition.getType() == Type.stringType) {
                leftExpressionAddition.genC(pw);
                pw.print(".data != ");
                rightExpressionAddition.genC(pw);
                pw.print(".data");
            }
        } else {
            leftExpressionAddition.genC(pw);
            if (relationalOperator != null) {
                pw.print(" ");
                relationalOperator.genC(pw);
                pw.print(" ");
                rightExpressionAddition.genC(pw);
            }
        }

    }

    public Type getType() {
        if (relationalOperator == null)
            return leftExpressionAddition.getType();
        else
            return Type.booleanType;
    }

    public boolean isIdentifier() {
        return this.relationalOperator == null && this.leftExpressionAddition.isIdentifier();
    }

    public boolean isFunctionWithReturn() {
        return this.relationalOperator == null && this.leftExpressionAddition.isFunctionWithReturn();
    }

    public Function getFunction() {
        if (this.relationalOperator == null)
            return this.leftExpressionAddition.getFunction();
        else
            return null;
    }
}
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

package AST;

import java.util.Iterator;
import java.util.List;

public class ExpressionAnd implements Printable {

    private List<ExpressionRelational> expressionRelationals;

    public ExpressionAnd(List<ExpressionRelational> expressionRelationals) {
        this.expressionRelationals = expressionRelationals;
    }

    @Override
    public void genC(PW pw) {
        expressionRelationals.get(0).genC(pw);
        expressionRelationals.stream().skip(1).forEach(expressionRelational -> {
            pw.print(" && ");
            expressionRelational.genC(pw);
        });
    }
}
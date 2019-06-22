/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

public class Expression implements Printable {

    private List<ExpressionAnd> expressionAnds;

    public Expression(List<ExpressionAnd> expressionAnds) {
        this.expressionAnds = expressionAnds;
    }

    @Override
    public void genC(PW pw) {
        expressionAnds.get(0).genC(pw);
        expressionAnds.stream().skip(1).forEach(expressionAnd -> {
            pw.print(" || ");
            expressionAnd.genC(pw);
        });
    }
    
    public Type getType() {
    	if( this.expressionAnds.size() == 1)
    		return this.expressionAnds.get(0).getType();
    	else
    		return new BooleanType();
    }
    
    public boolean isIdentifier() {
    	return this.expressionAnds.size() == 1
    			&& this.expressionAnds.get(0).isIdentifier();
	}

}
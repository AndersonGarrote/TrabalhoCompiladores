/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

public class IfStatement extends Statement {

    private Expression expression;
    private List<Statement> statementsTrue;
    private List<Statement> statementsFalse;

    public IfStatement(Expression expression, List<Statement> statementsTrue, List<Statement> statementsFalse) {
        this.expression = expression;
        this.statementsTrue = statementsTrue;
        this.statementsFalse = statementsFalse;
    }

    @Override
    public void genC(PW pw) {
    	pw.print( "if ( " );
    	
    	this.expression.genC(pw);
    	
    	pw.print(" ) {");
    	pw.breakLine();
    	
    	for (Statement stat : this.statementsTrue) {
			
			stat.genC(pw);
			pw.breakLine();
		}
    	
    	pw.print("} ");
    	pw.breakLine();
    	
    	if (this.statementsFalse != null) {
    		
    		pw.print( "else {" );
        	pw.breakLine();
        	
        	for (Statement stat : this.statementsFalse) {
    			
    			stat.genC(pw);
    			pw.breakLine();
    		}
        	
        	pw.print("} ");
        	pw.breakLine();
        }
    	
        pw.breakLine();
    }

}
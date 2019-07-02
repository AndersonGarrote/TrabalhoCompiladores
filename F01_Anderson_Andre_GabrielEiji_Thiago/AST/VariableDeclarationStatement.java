/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

public class VariableDeclarationStatement extends Statement {

    private Variable variable;

    public VariableDeclarationStatement() {
        
    }

    public VariableDeclarationStatement(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void genC(PW pw) {
    	
    	this.variable.getType().genC(pw);
    	
    	pw.print(" ");
    	
    	this.variable.getIdentifier().genC(pw);
    	
    	if( this.variable.getType() == Type.stringType )
    		pw.print("[]");
    	
    	pw.print(";");
    	pw.breakLine();
    		
    }

}
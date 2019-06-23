/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;

public class WriteFunction extends Function {

    public WriteFunction() {
        super(new Identifier("write"));
	}

	public WriteFunction(Identifier identifier) {
		super(identifier);
	}
	
	public boolean validateParameters(List<Expression> parameters) {

		if(parameters.size() != 1) {
			return false;
		}

		Type parameterType = parameters.get(0).getType();

		if(parameterType != Type.integerType && parameterType != Type.stringType) {
			return false;
		}

		return true;

	}

}
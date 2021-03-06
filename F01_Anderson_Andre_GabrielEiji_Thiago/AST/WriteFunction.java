/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.util.List;
import java.util.ArrayList;

public class WriteFunction extends Function {

    public WriteFunction() {
		this(new Identifier("write"));
	}

	public WriteFunction(Identifier identifier) {
        super(identifier);
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new Parameter(new Identifier("type"), Type.stringType));
		setParameters(parameters);
	}
	
	@Override
	public ParamsValidation validateParameters(List<Expression> parameters) {

		if(parameters.size() == 0) {
			return ParamsValidation.WRONG_PARAM_NUMBER;
		}

		Type parameterType = parameters.get(0).getType();

		if(parameterType != Type.integerType && parameterType != Type.stringType) {
			return ParamsValidation.WRONG_PARAM_TYPE;
		}

		return ParamsValidation.VALID_PARAMS;

	}

}
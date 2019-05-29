package AST;

import java.util.List;

public class Program implements Printable {

	private List<Function> functions;

	public Program(List<Function> functions) {
		this.functions = functions;
	}

	public void genC(PW pw) {
		for (Function function : functions) {
			function.genC(pw);
		}
	}

}

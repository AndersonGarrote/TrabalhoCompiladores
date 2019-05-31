/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
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

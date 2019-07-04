/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AST;

import java.io.PrintWriter;

public class PW {

	private String currentIndent = "";
	public PrintWriter out;

	public void set(PrintWriter out) {
		this.out = out;
		this.currentIndent = "";
	}

	public void add() {
		this.currentIndent += "\t";
	}

	public void sub() {
		this.currentIndent = currentIndent.substring(0, currentIndent.length() - 1);
	}

	public void print(String s) {
		this.out.print(s);
	}

	public void breakLine() {
		this.out.print("\r\n" + currentIndent);
	}

	public void breakLine(boolean sub) {
		if (sub) {
			sub();
		}
		breakLine();
		add();
	}

}

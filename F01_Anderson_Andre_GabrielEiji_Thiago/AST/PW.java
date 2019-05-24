package AST;

import java.io.PrintWriter;

public class PW {

	private int currentIndent = 0;

	public int step = 3;
	public PrintWriter out;

	public void set(PrintWriter out) {
		this.out = out;
		this.currentIndent = 0;
	}

	public void set(int indent) {
		this.currentIndent = indent;
	}

	public void add() {
		this.currentIndent+= step;
	}

	public void sub() {
		this.currentIndent-= step;
	}

	public void print(String s) {
		this.out.print("".substring(0, currentIndent));
		this.out.print(s);
	}

}

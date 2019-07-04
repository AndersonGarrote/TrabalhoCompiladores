/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package AuxComp;

import Lexer.*;
import java.io.*;

public class CompilerError {
	private Lexer lexer;
	private PrintWriter out;
	private boolean thereWasAnError;
	private String fileName;

	public CompilerError(Lexer lexer, PrintWriter out, String fileName) {
		// output of an error is done in out
		this.lexer = lexer;
		this.out = out;
		this.fileName = fileName;
		thereWasAnError = false;
	}

	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
	}

	public boolean wasAnErrorSignalled() {
		return thereWasAnError;
	}

	public void show(String strMessage) {
		show(strMessage, false);
	}

	public void show(String strMessage, boolean goPreviousToken) {

		out.print("\n");
		// Nome do arquivo
		out.print(fileName);

		// previous token, not the last one.
		if (goPreviousToken) {
			out.print(":" + lexer.getLineNumberBeforeLastToken() + ": ");
			out.println(strMessage);
			out.print(lexer.getLineBeforeLastToken());
		} else {
			out.print(":" + lexer.getLineNumber() + ": ");
			out.println(strMessage);
			out.print(lexer.getCurrentLine());
		}
		out.flush();
		if (out.checkError())
			System.out.println("Error in signaling an error");
		thereWasAnError = true;
	}

	public void signal(String strMessage) {
		show(strMessage);
		out.flush();
		thereWasAnError = true;
		throw new RuntimeException();
	}

	private void signalWrongToken(String token) {
		signal("Esperado o token " + token + ", encontrado " + "'" + lexer.token + "'");
	}

	public void signalWrongToken(Symbol expected) {
		signalWrongToken("'" + expected.toString() + "'");
	}

	public void signalWrongToken(Symbol... expected) {
		String tokens = "'" + expected[0].toString() + "'";
		for(int i = 1; i < expected.length; i++) {
			tokens += " | '" + expected[i].toString() + "'";
		}
		signalWrongToken(tokens);
	}
}
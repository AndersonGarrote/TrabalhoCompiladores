/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package Lexer;

import java.util.*;
import AuxComp.CompilerError;

public class Lexer {

	public Symbol token;
	public int numberValue;
	public String stringValue;

	char[] input;
	int lineNumber;
	int tokenPos, lastTokenPos, beforeLastTokenPos;

	CompilerError error;

	// contains the keywords
	static private Hashtable<String, Symbol> keywordsTable;
	// this code will be executed only once for each program execution
	static {
		keywordsTable = new Hashtable<String, Symbol>();
		keywordsTable.put("function", Symbol.FUNCTION);
		keywordsTable.put("var", Symbol.VAR);
		keywordsTable.put("Boolean", Symbol.BOOLEAN);
		keywordsTable.put("String", Symbol.STRING);
		keywordsTable.put("Int", Symbol.INTEGER);
		keywordsTable.put("if", Symbol.IF);
		keywordsTable.put("else", Symbol.ELSE);
		keywordsTable.put("while", Symbol.WHILE);
		keywordsTable.put("write", Symbol.WRITE);
		keywordsTable.put("writeln", Symbol.WRITELN);
		keywordsTable.put("true", Symbol.TRUE);
		keywordsTable.put("false", Symbol.FALSE);
		keywordsTable.put("and", Symbol.AND);
		keywordsTable.put("or", Symbol.OR);
		keywordsTable.put("return", Symbol.RETURN);
	}

	public Lexer(char[] input, CompilerError error) {
		this.input = input;
		// add an end-of-file label to make it easy to do the lexer
		input[input.length - 1] = '\0';
		// number of the current line
		lineNumber = 1;
		tokenPos = 0;
		lastTokenPos = 0;
		beforeLastTokenPos = 0;
		this.error = error;
	}

	public Symbol nextToken() {
		
		char ch;

		while ((ch = input[tokenPos]) == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {

			// conta o número de linhas
			if (ch == '\n') {
				lineNumber++;
			}
			tokenPos++;
		}
		if (ch == '\0') {
			token = Symbol.EOF;
		} else {
			if (Character.isLetter(ch)) {
				StringBuffer ident = new StringBuffer();
				while (Character.isLetter(input[tokenPos])) {
					ident.append(input[tokenPos]);
					tokenPos++;
				}
				stringValue = ident.toString();
				Symbol value = keywordsTable.get(stringValue);
				if (value == null) {
					token = Symbol.IDENTIFIER;
				} else {
					token = value;
				}
				if (Character.isDigit(input[tokenPos])) {
					error.signal("Word followed by a number");
				}
			} else if (Character.isDigit(ch)) {
				StringBuffer number = new StringBuffer();
				while (Character.isDigit(input[tokenPos])) {
					number.append(input[tokenPos]);
					tokenPos++;
				}
				try {
					numberValue = Integer.valueOf(number.toString()).intValue();
					token = Symbol.NUMBER;
				} catch (NumberFormatException e) {
					error.signal("Number out of limits");
				}
			} else {
				tokenPos++;
				switch (ch) {
				case '+':
					token = Symbol.PLUS;
					break;

				case '-':
					if (input[tokenPos] == '>') {
						tokenPos++;
						token = Symbol.ARROW;
					} else {
						token = Symbol.MINUS;
					}
					break;

				case '*':
					token = Symbol.TIMES;
					break;

				case '/':
					token = Symbol.DIVISION;
					break;

				case '<':
					if (input[tokenPos] == '=') {
						tokenPos++;
						token = Symbol.LESS_OR_EQUAL;
					} else {
						token = Symbol.LESS;
					}
					break;

				case '>':
					if (input[tokenPos] == '=') {
						tokenPos++;
						token = Symbol.GREATER_OR_EQUAL;
					} else {
						token = Symbol.GREATER;
					}
					break;

				case '=':
					if (input[tokenPos] == '=') {
						tokenPos++;
						token = Symbol.EQUAL;
					} else {
						token = Symbol.ASSIGN;
					}
					break;

				case '(':
					token = Symbol.LEFT_PARENTHESIS;
					break;

				case ')':
					token = Symbol.RIGHT_PARENTHESIS;
					break;

				case '{':
					token = Symbol.LEFT_CURLY_BRACKET;
					break;

				case '}':
					token = Symbol.RIGHT_CURLY_BRACKET;
					break;

				case ',':
					token = Symbol.COMMA;
					break;

				case ';':
					token = Symbol.SEMICOLON;
					break;

				case ':':
					token = Symbol.COLON;
					break;

				case '\"':
					StringBuffer word = new StringBuffer();
					while (input[tokenPos] != '\"' && input[tokenPos] != '\0') {
						word.append(input[tokenPos]);
						tokenPos++;
					}
					stringValue = word.toString();
					token = Symbol.WORD;
					if (input[tokenPos] != '\"') {
						error.signal(" Expected \"");
					}
					tokenPos++;
					break;

				default:
					error.signal("invalid Character: '" + ch + "'");
				}
			}
			beforeLastTokenPos = lastTokenPos;
			lastTokenPos = tokenPos - 1;
			
		}

		// System.out.println(token);

		return token;

	}

	// return the line number of the last token got with getToken()
	public int getLineNumber() {
		return lineNumber;
	}

	public int getLineNumberBeforeLastToken() {
		return getLineNumber(beforeLastTokenPos);
	}

	private int getLineNumber(int index) {
		// return the line number in which the character input[index] is
		int i, n, size;
		n = 1;
		i = 0;
		size = input.length;
		while (i < size && i < index) {
			if (input[i] == '\n')
				n++;
			i++;
		}
		return n;
	}

	public String getCurrentLine() {
		return getLine(lastTokenPos);
	}

	public String getLineBeforeLastToken() {
		return getLine(beforeLastTokenPos);
	}

	private String getLine(int index) {
		// get the line that contains input[index]. Assume input[index] is at a token,
		// not
		// a white space or newline
		int i = index;
		if (i == 0)
			i = 1;
		else if (i >= input.length)
			i = input.length;
		StringBuffer line = new StringBuffer();
		// go to the beginning of the line
		while (i >= 1 && input[i] != '\n')
			i--;
		if (input[i] == '\n')
			i++;
		// go to the end of the line putting it in variable line
		while (input[i] != '\0' && input[i] != '\n' && input[i] != '\r') {
			line.append(input[i]);
			i++;
		}
		return line.toString();
	}

	public String getStringValue() {
		return stringValue;
	}

	public int getNumberValue() {
		return numberValue;
	}

}
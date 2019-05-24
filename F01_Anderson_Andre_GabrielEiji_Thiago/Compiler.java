import java.io.PrintWriter;
import java.util.Arrays;

import Lexer.*;
import AST.*;

public class Compiler {
	
	private SymbolTable symbolTable;
	private Lexer lexer;
	//private CompileError error;
	private char []input;


	private static final String[] TYPES = {"Int", "Boolean", "String"};

	public Program compile(char[] input, PrintWriter printWriter) {
		return null;
	}

	public void Program() {
		Func();
		while (token != '0') // MUDAR PARA EOF
			Func();
	}

	public void Func() {
		if (token == 'F') {
			Id();
			switch (token) {
			case '(':
				nextToken();
				ParamList();

				if (token == ')') {
					nextToken();
				} else
					error();
				break;

			case '-':
				nextToken();
				if (token == '>') {
					nextToken();
					Type();
				} else
					error();
				break;
			}

			StatList();
		} else {
			error();
		}
	}

	public void ParamList() {
		ParamDec();

		while (token != ',')
			ParamDec();
	}

	public void ParamDec() {
		Id();
		if (token == ':') {
			nextToken();
			Type();
		} else {
			error();
		}
	}

	public void Type() {

		// if(!Arrays.stream(TYPES).anyMatch(x -> x.equals(token))) {
		// 	error();
		// }

		if (token != 'I' && token != 'B' && token != 'S') {
			error();
		}
	}

	public void StatList() {
		if (token == '{') {
			while (token != '}')
				Stat();
		}
	}

	public void Stat() {

	}

	public void AssingExprStat() {
		Expr();
		switch (token) {
		case '=':
			nextToken();
			Expr();
			break;

		case ';':
			nextToken();
			break;

		default:
			error();
			break;
		}
	}

	public void ReturnStat() {

	}

	public void VarDecStat() {

	}

	public void IfStat() {

	}

	public void WhileStat() {

	}

	public void Expr() {

	}

	public void ExprAnd() {

	}

	public void ExprRel() {

	}

	public void RelOp() {
		switch (token) {
		case '<':
			nextToken();
			break;

		case '>':
			nextToken();
			break;

		default:
			error();
			break;
		}
	}

	public void ExprAdd() {
		ExprMult();
		while (token == '+' || token == '-') {
			nextToken();
			ExprMult();
		}
	}

	public void ExprMult() {
		ExprUnary();
		while (token == '*' || token == '/') {
			nextToken();
			ExprUnary();
		}
	}

	public void ExprUnary() {
		if (token == '+' || token == '-') {
			nextToken();
		}
		ExprPrimary();
	}

	public void ExprPrimary() {
<<<<<<< HEAD
		//Id() FuncCall() ExprLiteral()

		//Verifica se é LiteralInt

		//Senão, verifica se tem um Id
			//nextToken()
			//Verifica se tem () e é uma função
	}

	public void ExprLiteral() {
		//LiteralInt() LiteralBoolean() LiteralString()

		//Verifica se começa com "
			//LiteralString()

		//Verifica se começa com t ou f
			//LiteralBoolean
=======
		// Id() FuncCall() ExprLiteral()
	}

	public void ExprLiteral() {
		// LiteralInt() LiteralBoolean() LiteralString()
>>>>>>> uema
	}

	public void LiteralBoolean() {
		switch (token) {
		case 't':
			nextToken();
			break;

		case 'f':
			nextToken();
			break;
		}
	}

	public void FuncCall() {
		Id();
		if (token == '(') {

			nextToken();

			if (token == ')') {
				nextToken();

			} else {
				Expr();

				while (token == ',') {
					nextToken();
					Expr();
				}

				if (token == ')') {
					nextToken();
				} else {
					error();
				}
			}
		}
	}

	public void nextToken() {

		while (tokenPos < input.length && input[tokenPos] == ' ') {
			tokenPos++;
		}

		if (tokenPos >= input.length)
			token = '\0';
		else {
			token = input[tokenPos];
			tokenPos++;
		}
	}

	public void Id() {

	}

	public void error() {
		if (tokenPos == 0)
			tokenPos = 1;
		else if (tokenPos >= input.length)
			tokenPos = input.length;

		String strInput = new String(input, tokenPos - 1, input.length - tokenPos + 1);
		String strError = "Error at \"" + strInput + "\"";
		System.out.println(strError);
		throw new RuntimeException(strError);
	}

<<<<<<< HEAD
	
=======
	private char token;
	private int tokenPos;
	private char[] input;

>>>>>>> uema
}

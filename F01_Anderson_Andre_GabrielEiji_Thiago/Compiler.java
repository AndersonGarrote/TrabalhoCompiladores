import java.io.PrintWriter;
import java.util.*;

import Lexer.*;
import AST.*;

public class Compiler {
	
	private Hashtable<String, Variable> symbolTable;
	private Lexer lexer;
	//private CompileError error;
	private CompileError error;
	char [] input;

	private static final String[] TYPES = {"Int", "Boolean", "String"};

	public void compile(char[] p_input, PrintWriter printWriter) {
//		symbolTable = new Hashtable<String, Variable>();
//		input = p_input;
//		error = new CompilerError( outError );
//		lexer = new Lexer(input, error);
//		error.setLexer(lexer);
		
		lexer.nextToken();
		//return Program();
	}

	public void Program() {
		Func();
		while (lexer.token != Symbol.EOF) // MUDAR PARA EOF
			Func();
	}

	public void Func() {
		if (lexer.token == Symbol.FUNCTION) {
			lexer.nextToken();
			
			Id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				lexer.nextToken();
				ParamList();

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					lexer.nextToken();
				} else {
					error("Esperado token ).");
				}
			}

			if(lexer.token == Symbol.ARROW) {
				lexer.nextToken();
				Type();
			}

			StatList();
		} else {
			error("Esperado o token function.");
		}
	}

	public void ParamList() {
		ParamDec();

		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			ParamDec();
		}
	}

	public void ParamDec() {
		Id();
		if (lexer.token == Symbol.COLON) {
			lexer.nextToken();
			Type();
		} else {
			error("Esperado o token :.");
		}
	}

	public void Type() {

		if (lexer.token != Symbol.INTEGER && lexer.token != Symbol.BOOLEAN && lexer.token != Symbol.STRING) {
			error("Esperado token de tipo (Int, Boolean ou String).");
		}
		else {
			lexer.nextToken();
		}
	}

	public void StatList() {
		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {
			lexer.nextToken();
			while (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
				Stat();
			}
			lexer.nextToken();
		}
	}

	public void Stat() {
		if(lexer.token == Symbol.VAR) {
			VarDecStat();
		} else if(lexer.token == Symbol.RETURN) {
			ReturnStat();
		} else if(lexer.token == Symbol.IF) {
			IfStat();
		} else if(lexer.token == Symbol.WHILE) {
			WhileStat();
		} else {
			AssignExprStat();
		}
			
	}

	public void AssignExprStat() {
		Expr();
		if(lexer.token == Symbol.ASSIGN) {
			lexer.nextToken();
			Expr();
		}
		if(lexer.token == Symbol.SEMICOLON) {
			lexer.nextToken();
		} else {
			error("Esperado o token ;.");
		}
	}

	public void ReturnStat() {
		if(lexer.token == Symbol.RETURN) {
			lexer.nextToken();
			Expr();
			
			if(lexer.token == Symbol.SEMICOLON) {
				lexer.nextToken();
			} else {
				error("Esperado o token ;.");
			}
		} else {
			error("Esperado o token =.");
		}
	}

	public void VarDecStat() {
		if(lexer.token == Symbol.VAR) {
			lexer.nextToken();
			
			Id();
			
			if(lexer.token == Symbol.COLON) {
				lexer.nextToken();
				
				Type();
				
				if(lexer.token == Symbol.SEMICOLON) {
					lexer.nextToken();
				} else {
					error("Esperado o token ;.");
				}
			} else {
				error("Esperado o token :.");
			}
		} else {
			error("Esperado o token var.");
		}
	}

	public void IfStat() {
		if(lexer.token == Symbol.IF) {
			lexer.nextToken();
			
			Expr();
			StatList();
			
			if(lexer.token == Symbol.ELSE) {
				lexer.nextToken();
				
				StatList();
			}
		} else {
			error("Esperado o token if.");
		}
	}

	public void WhileStat() {
		if(lexer.token == Symbol.WHILE) {
			lexer.nextToken();
			
			Expr();
			StatList();
			
		} else {
			error("Esperado o token while.");
		}
	}

	public void Expr() {
		ExprAnd();
		
		while(lexer.token == Symbol.OR) {
			lexer.nextToken();
			
			ExprAnd();
		}
	}

	public void ExprAnd() {
		ExprRel();	
		
		while(lexer.token == Symbol.AND) {
			lexer.nextToken();
			
			ExprRel();
		}
	}

	public void ExprRel() {
		ExprAdd();
		
		if(lexer.token == Symbol.GREATER || lexer.token == Symbol.GREATER_OR_EQUAL || 
		   lexer.token == Symbol.LESS || lexer.token == Symbol.LESS_OR_EQUAL || 
		   lexer.token == Symbol.EQUAL || lexer.token == Symbol.NOT_EQUAL) {
			RelOp();
			ExprAdd();
		}
	}

	public void RelOp() {
		switch (lexer.token) {
		case LESS:
			lexer.nextToken();
			break;

		case LESS_OR_EQUAL:
			lexer.nextToken();
			break;
		
		case GREATER:
			lexer.nextToken();
			break;

		case GREATER_OR_EQUAL:
			lexer.nextToken();
			break;
		
		case EQUAL:
			lexer.nextToken();
			break;
		
		case NOT_EQUAL:
			lexer.nextToken();
			break;
		
		default:
			error("Esperado o token relacional (<, <=, >, >=, ==, !=)");
			break;
		}
	}

	public void ExprAdd() {
		ExprMult();
		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			lexer.nextToken();
			ExprMult();
		}
	}

	public void ExprMult() {
		ExprUnary();
		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {
			lexer.nextToken();
			ExprUnary();
		}
	}

	public void ExprUnary() {
		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			lexer.nextToken();
		}
		ExprPrimary();
	}

	public void ExprPrimary() {
		if(lexer.token == Symbol.IDENTIFIER) {
			Id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				FuncCall();
			}
		}
		else {
			ExprLiteral();
		}
	}

	public void ExprLiteral() {
		switch(lexer.token) {
			case WORD:
				LiteralString();
				break;
			
			case NUMBER:
				LiteralInt();
				break;
			
			case TRUE:
			case FALSE:
				LiteralBoolean();
				break;
				
			default:
				error("Esperado uma expressão literal.");
				break;
		}
	}

	public void LiteralBoolean() {
		switch (lexer.token) {
		case TRUE:
			lexer.nextToken();
			break;

		case FALSE:
			lexer.nextToken();
			break;
		}
	}
	
	public void LiteralInt() {
		if(lexer.token == Symbol.NUMBER) {
			lexer.nextToken();
		}
		else {
			error("Esperado um número inteiro.");
		}
	}
	
	public void LiteralString() {
		if(lexer.token == Symbol.WORD) {
			lexer.nextToken();
		} else {
			error("Esperado uma string.");
		}
	}

	public void FuncCall() {
		//Id(); Apenas FuncCall() possui "(" após a chamada de Id().
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				lexer.nextToken();
				
			} else {
				Expr();

				while (lexer.token == Symbol.COMMA) {
					lexer.nextToken();
					Expr();
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					lexer.nextToken();
				} else {
					error("Esperado o token ).");
				}
			}
		} else {
			error("Esperado o token (.");
		}
	}

	public void Id() {
		if(lexer.token == Symbol.IDENTIFIER) {
			lexer.nextToken();
		} else {
			error("Esperado um identificador.");
		}
	}

	public void error(String strInput) {
		
		String strError = "ERROR: \"" + strInput + "\"";
		System.out.println(strError);
		throw new RuntimeException(strError);
	}
}

import java.io.PrintWriter;
import Lexer.*;
import AST.*;
import AuxComp.*;

public class Compiler {
	
	//private Hashtable<String, Variable> symbolTable;
	private Lexer lexer;
	private CompilerError error;
	char [] input;
	PrintWriter outError;

	//private static final String[] TYPES = {"Int", "Boolean", "String"};

	public Compiler() {
	}

	public Program compile(char[] p_input, PrintWriter printWriter) {
//		symbolTable = new Hashtable<String, Variable>();
		input = p_input;
		outError = printWriter;
		error = new CompilerError( lexer, outError );
		lexer = new Lexer(input, error);
		error.setLexer(lexer);
		
		System.out.println(lexer.token); lexer.nextToken();
		Program();
		return null;//Program();
	}

	public void Program() {
		System.out.println("Program");
		Func();
		while (lexer.token != Symbol.EOF)
			Func();
	}

	public void Func() {
		if (lexer.token == Symbol.FUNCTION) {
			System.out.println(lexer.token); lexer.nextToken();
			
			Id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				ParamList();

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado token \")\".");
				}
			}
			if(lexer.token == Symbol.ARROW) {
				System.out.println(lexer.token); lexer.nextToken();
				Type();
			}
			
			StatList();
		} else {
			error.signal("Esperado o token \"function\".");
		}
	}

	public void ParamList() {
		ParamDec();

		while (lexer.token == Symbol.COMMA) {
			System.out.println(lexer.token); lexer.nextToken();
			ParamDec();
		}
	}

	public void ParamDec() {
		Id();
		if (lexer.token == Symbol.COLON) {
			System.out.println(lexer.token); lexer.nextToken();
			Type();
		} else {
			error.signal("Esperado o token \":\".");
		}
	}

	public void Type() {

		if (lexer.token != Symbol.INTEGER && lexer.token != Symbol.BOOLEAN && lexer.token != Symbol.STRING) {
			error.signal("Esperado token de tipo (\"Int\", \"Boolean\" ou \"String\").");
		}
		else {
			System.out.println(lexer.token); lexer.nextToken();
		}
	}

	public void StatList() {
		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {
			System.out.println(lexer.token); lexer.nextToken();
			while (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
				Stat();
			}
			System.out.println(lexer.token); lexer.nextToken();
		}
		else {
			error.signal("Esperado o token \"{\"");
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
			System.out.println(lexer.token); lexer.nextToken();
			Expr();
		}
		if(lexer.token == Symbol.SEMICOLON) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado o token \";\".");
		}
	}

	public void ReturnStat() {
		if(lexer.token == Symbol.RETURN) {
			System.out.println(lexer.token); lexer.nextToken();
			Expr();
			
			if(lexer.token == Symbol.SEMICOLON) {
				System.out.println(lexer.token); lexer.nextToken();
			} else {
				error.signal("Esperado o token \";\".");
			}
		} else {
			error.signal("Esperado o token \"=\".");
		}
	}

	public void VarDecStat() {
		if(lexer.token == Symbol.VAR) {
			System.out.println(lexer.token); lexer.nextToken();
			
			Id();
			
			if(lexer.token == Symbol.COLON) {
				System.out.println(lexer.token); lexer.nextToken();
				
				Type();
				
				if(lexer.token == Symbol.SEMICOLON) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado o token \";\".");
				}
			} else {
				error.signal("Esperado o token \":\".");
			}
		} else {
			error.signal("Esperado o token \"var\".");
		}
	}

	public void IfStat() {
		if(lexer.token == Symbol.IF) {
			System.out.println(lexer.token); lexer.nextToken();
			
			Expr();
			StatList();
			
			if(lexer.token == Symbol.ELSE) {
				System.out.println(lexer.token); lexer.nextToken();
				
				StatList();
			}
		} else {
			error.signal("Esperado o token \"if\".");
		}
	}

	public void WhileStat() {
		if(lexer.token == Symbol.WHILE) {
			System.out.println(lexer.token); lexer.nextToken();
			
			Expr();
			StatList();
			
		} else {
			error.signal("Esperado o token \"while\".");
		}
	}

	public void Expr() {
		ExprAnd();
		
		while(lexer.token == Symbol.OR) {
			System.out.println(lexer.token); lexer.nextToken();
			
			ExprAnd();
		}
	}

	public void ExprAnd() {
		ExprRel();	
		
		while(lexer.token == Symbol.AND) {
			System.out.println(lexer.token); lexer.nextToken();
			
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
			System.out.println(lexer.token); lexer.nextToken();
			break;

		case LESS_OR_EQUAL:
			System.out.println(lexer.token); lexer.nextToken();
			break;
		
		case GREATER:
			System.out.println(lexer.token); lexer.nextToken();
			break;

		case GREATER_OR_EQUAL:
			System.out.println(lexer.token); lexer.nextToken();
			break;
		
		case EQUAL:
			System.out.println(lexer.token); lexer.nextToken();
			break;
		
		case NOT_EQUAL:
			System.out.println(lexer.token); lexer.nextToken();
			break;
		
		default:
			error.signal("Esperado o token relacional (\"<\", \"<=\", \">\", \">=\", \"==\", \"!=\")");
			break;
		}
	}

	public void ExprAdd() {
		ExprMult();
		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			System.out.println(lexer.token); lexer.nextToken();
			ExprMult();
		}
	}

	public void ExprMult() {
		ExprUnary();
		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {
			System.out.println(lexer.token); lexer.nextToken();
			ExprUnary();
		}
	}

	public void ExprUnary() {
		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			System.out.println(lexer.token); lexer.nextToken();
		}
		ExprPrimary();
	}

	public void ExprPrimary() {
		if(lexer.token == Symbol.WRITE) {
			System.out.println(lexer.token); lexer.nextToken();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				WriteCall();
			}
		}else if(lexer.token == Symbol.WRITELN) {
			System.out.println(lexer.token); lexer.nextToken();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				WritelnCall();
			}
		}else if(lexer.token == Symbol.IDENTIFIER) {
			Id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				FuncCall();
			}
		}
		else {
			ExprLiteral();
		}
	}

	private void WritelnCall() {
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				Expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					Expr();
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado o token \")\".");
				}
			}
		} else {
			error.signal("Esperado o token \"(\".");
		}
	}

	private void WriteCall() {
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				Expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					Expr();
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado o token \")\".");
				}
			}
		} else {
			error.signal("Esperado o token \"(\".");
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
				error.signal("Esperado uma expressão literal.");
				break;
		}
	}

	public void LiteralBoolean() {
		switch (lexer.token) {
			case TRUE:
				System.out.println(lexer.token); lexer.nextToken();
				break;
	
			case FALSE:
				System.out.println(lexer.token); lexer.nextToken();
				break;
			default:
				error.signal("Esperado um valor booleano(\"true\" ou \"false\").");
				break;
			}
	}
	
	public void LiteralInt() {
		if(lexer.token == Symbol.NUMBER) {
			System.out.println(lexer.token); lexer.nextToken();
		}
		else {
			error.signal("Esperado um número inteiro.");
		}
	}
	
	public void LiteralString() {
		if(lexer.token == Symbol.WORD) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado uma string.");
		}
	}

	public void FuncCall() {
		//Id(); Apenas FuncCall() possui "(" após a chamada de Id().
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				Expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					Expr();
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado o token \")\".");
				}
			}
		} else {
			error.signal("Esperado o token \"(\".");
		}
	}

	public void Id() {
		if(lexer.token == Symbol.IDENTIFIER) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado um identificador.");
		}
	}

}

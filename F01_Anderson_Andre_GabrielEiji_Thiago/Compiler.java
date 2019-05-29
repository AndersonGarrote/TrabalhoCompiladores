import java.io.PrintWriter;
import java.util.*;
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
		program();
		return null;//Program();
	}

	public Program program() {
		System.out.println("Program");
		
		List<Function> funcList = new ArrayList<Function>();  
		
		funcList.add(func());
		
		while (lexer.token != Symbol.EOF) {
			
			funcList.add(func());
		
		}
		
		return new Program(funcList);
	}

	public Function func() {
		
		Identifier id = null;
		ParameterList parList = null;
		Type ret = null;
		StatementList stList = null;
		
		if (lexer.token == Symbol.FUNCTION) {
			System.out.println(lexer.token); lexer.nextToken();
			
			 id = id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
				parList = paramList();

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token); lexer.nextToken();
				} else {
					error.signal("Esperado token \")\".");
				}
			}
			if(lexer.token == Symbol.ARROW) {
				System.out.println(lexer.token); lexer.nextToken();
				ret = type();
			}
			
			stList = statList();
		} else {
			error.signal("Esperado o token \"function\".");
		}
		
		return new Function(id, parList, ret, stList);
	}

	public void paramList() {
		
		paramDec();

		while (lexer.token == Symbol.COMMA) {
			System.out.println(lexer.token); lexer.nextToken();
			paramDec();
		}
	}

	public void paramDec() {
		id();
		if (lexer.token == Symbol.COLON) {
			System.out.println(lexer.token); lexer.nextToken();
			type();
		} else {
			error.signal("Esperado o token \":\".");
		}
	}

	public void type() {

		if (lexer.token != Symbol.INTEGER && lexer.token != Symbol.BOOLEAN && lexer.token != Symbol.STRING) {
			error.signal("Esperado token de tipo (\"Int\", \"Boolean\" ou \"String\").");
		}
		else {
			System.out.println(lexer.token); lexer.nextToken();
		}
	}

	public void statList() {
		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {
			System.out.println(lexer.token); lexer.nextToken();
			while (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
				stat();
			}
			System.out.println(lexer.token); lexer.nextToken();
		}
		else {
			error.signal("Esperado o token \"{\"");
		}
	}

	public void stat() {
		if(lexer.token == Symbol.VAR) {
			varDecStat();
		} else if(lexer.token == Symbol.RETURN) {
			returnStat();
		} else if(lexer.token == Symbol.IF) {
			ifStat();
		} else if(lexer.token == Symbol.WHILE) {
			whileStat();
		} else {
			assignExprStat();
		}
			
	}

	public void assignExprStat() {
		expr();
		if(lexer.token == Symbol.ASSIGN) {
			System.out.println(lexer.token); lexer.nextToken();
			expr();
		}
		if(lexer.token == Symbol.SEMICOLON) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado o token \";\".");
		}
	}

	public void returnStat() {
		if(lexer.token == Symbol.RETURN) {
			System.out.println(lexer.token); lexer.nextToken();
			expr();
			
			if(lexer.token == Symbol.SEMICOLON) {
				System.out.println(lexer.token); lexer.nextToken();
			} else {
				error.signal("Esperado o token \";\".");
			}
		} else {
			error.signal("Esperado o token \"return\".");
		}
	}

	public void varDecStat() {
		if(lexer.token == Symbol.VAR) {
			System.out.println(lexer.token); lexer.nextToken();
			
			id();
			
			if(lexer.token == Symbol.COLON) {
				System.out.println(lexer.token); lexer.nextToken();
				
				type();
				
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

	public void ifStat() {
		if(lexer.token == Symbol.IF) {
			System.out.println(lexer.token); lexer.nextToken();
			
			expr();
			statList();
			
			if(lexer.token == Symbol.ELSE) {
				System.out.println(lexer.token); lexer.nextToken();
				
				statList();
			}
		} else {
			error.signal("Esperado o token \"if\".");
		}
	}

	public void whileStat() {
		if(lexer.token == Symbol.WHILE) {
			System.out.println(lexer.token); lexer.nextToken();
			
			expr();
			statList();
			
		} else {
			error.signal("Esperado o token \"while\".");
		}
	}

	public void expr() {
		exprAnd();
		
		while(lexer.token == Symbol.OR) {
			System.out.println(lexer.token); lexer.nextToken();
			
			exprAnd();
		}
	}

	public void exprAnd() {
		exprRel();	
		
		while(lexer.token == Symbol.AND) {
			System.out.println(lexer.token); lexer.nextToken();
			
			exprRel();
		}
	}

	public void exprRel() {
		exprAdd();
		
		if(lexer.token == Symbol.GREATER || lexer.token == Symbol.GREATER_OR_EQUAL || 
		   lexer.token == Symbol.LESS || lexer.token == Symbol.LESS_OR_EQUAL || 
		   lexer.token == Symbol.EQUAL || lexer.token == Symbol.NOT_EQUAL) {
			relOp();
			exprAdd();
		}
	}

	public void relOp() {
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

	public void exprAdd() {
		exprMult();
		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			System.out.println(lexer.token); lexer.nextToken();
			exprMult();
		}
	}

	public void exprMult() {
		exprUnary();
		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {
			System.out.println(lexer.token); lexer.nextToken();
			exprUnary();
		}
	}

	public void exprUnary() {
		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			System.out.println(lexer.token); lexer.nextToken();
		}
		exprPrimary();
	}

	public void exprPrimary() {
		if(lexer.token == Symbol.WRITE) {
			System.out.println(lexer.token); lexer.nextToken();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				writeCall();
			}
		}else if(lexer.token == Symbol.WRITELN) {
			System.out.println(lexer.token); lexer.nextToken();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				writelnCall();
			}
		}else if(lexer.token == Symbol.IDENTIFIER) {
			id();
			
			if(lexer.token == Symbol.LEFT_PARENTHESIS) {
				funcCall();
			}
		}
		else {
			exprLiteral();
		}
	}

	private void writelnCall() {
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					expr();
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

	private void writeCall() {
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					expr();
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

	public void exprLiteral() {
		
		switch(lexer.token) {
			case WORD:
				literalString();
				break;
			
			case NUMBER:
				literalInt();
				break;
			
			case TRUE:
			case FALSE:
				literalBoolean();
				break;
				
			default:
				error.signal("Esperado uma expressão literal.");
				break;
		}
	}

	public void literalBoolean() {
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
	
	public void literalInt() {
		if(lexer.token == Symbol.NUMBER) {
			System.out.println(lexer.token); lexer.nextToken();
		}
		else {
			error.signal("Esperado um número inteiro.");
		}
	}
	
	public void literalString() {
		if(lexer.token == Symbol.WORD) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado uma string.");
		}
	}

	public void funcCall() {
		//Id(); Apenas FuncCall() possui "(" após a chamada de Id().
		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			System.out.println(lexer.token); lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token); lexer.nextToken();
				
			} else {
				expr();

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token); lexer.nextToken();
					expr();
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

	public void id() {
		if(lexer.token == Symbol.IDENTIFIER) {
			System.out.println(lexer.token); lexer.nextToken();
		} else {
			error.signal("Esperado um identificador.");
		}
	}

}

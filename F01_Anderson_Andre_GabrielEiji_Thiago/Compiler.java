
/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

import static AST.RelationalOperator.*;
import static AST.Type.*;

import java.io.PrintWriter;
import java.util.*;
import Lexer.*;
import AST.*;
import AuxComp.*;

public class Compiler {

	private Lexer lexer;
	private CompilerError error;
	char[] input;
	PrintWriter outError;

	SymbolTable symbolTable = new SymbolTable();

	public Compiler() {
		// Colocar as funções pré-definidas aqui
		Identifier write = new Identifier("write");
		write.setIdentifiable(new Function(write));
		symbolTable.put(write);
		Identifier writeln = new Identifier("writeln");
		writeln.setIdentifiable(new Function(writeln));
		symbolTable.put(writeln);
	}

	public Program compile(char[] p_input, PrintWriter printWriter, String fileName) {
		input = p_input;
		outError = printWriter;
		error = new CompilerError(lexer, outError, fileName);
		lexer = new Lexer(input, error);
		error.setLexer(lexer);
		lexer.nextToken();
		return program();
	}

	private Program program() {

		List<Function> functionList = new ArrayList<Function>();

		functionList.add(func());

		while (lexer.token != Symbol.EOF) {
			functionList.add(func());
		}

		return new Program(functionList);
	}

	private Function func() {

		if (lexer.token != Symbol.FUNCTION) {
			error.signalWrongToken(Symbol.FUNCTION);
		}

		lexer.nextToken();

		Identifier identifier = id();

		if (symbolTable.has(identifier)) {
			error.signal(identifier.getName() + " redeclarado");
		}

		symbolTable.put(identifier);

		List<Parameter> parameterList;

		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			lexer.nextToken();

			parameterList = paramList();

			if (lexer.token != Symbol.RIGHT_PARENTHESIS) {
				error.signalWrongToken(Symbol.RIGHT_PARENTHESIS);
			}

			lexer.nextToken();

		} else {
			parameterList = null;
		}

		Type type;

		if (lexer.token == Symbol.ARROW) {
			lexer.nextToken();
			type = type();
		} else {
			type = null;
		}
		
		List<Statement> statementList = statList();

		symbolTable.clearLocal();

		return new Function(identifier, parameterList, type, statementList);

	}

	private List<Parameter> paramList() {

		List<Parameter> parameters = new ArrayList<Parameter>();

		do {
			if (lexer.token == Symbol.COMMA) {

				lexer.nextToken();
			}
			parameters.add(paramDec());
		} while (lexer.token == Symbol.COMMA);

		return parameters;

	}

	private Parameter paramDec() {
		Identifier identifier = id();
		symbolTable.putLocal(identifier);
		if (lexer.token == Symbol.COLON) {

			lexer.nextToken();
			Type type = type();
			Variable variable = new Variable(identifier, type);
			
			return new Parameter(identifier, type);
		} else {
			error.signal("Esperado o token \":\".");
			return null;
		}
	}

	private Type type() {

		Type type = null;

		switch (lexer.token) {
		case BOOLEAN:
			type = booleanType;
			break;
		case INTEGER:
			type = integerType;
			break;
		case STRING:
			type = stringType;
			break;
		default:
			error.signal("Esperado token de tipo (\"Int\", \"Boolean\" ou \"String\").");
			return null;
		}

		lexer.nextToken();

		return type;

	}

	private List<Statement> statList() {

		List<Statement> statementList = new ArrayList<>();

		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {

			lexer.nextToken();

			while (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
				statementList.add(stat());
			}

			lexer.nextToken();

			return statementList;

		} else {
			error.signalWrongToken(Symbol.LEFT_CURLY_BRACKET);
			return null;
		}

	}

	private Statement stat() {

		Statement statement = null;

		if (lexer.token == Symbol.VAR) {
			statement = varDecStat();
		} else if (lexer.token == Symbol.RETURN) {
			statement = returnStat();
		} else if (lexer.token == Symbol.IF) {
			statement = ifStat();
		} else if (lexer.token == Symbol.WHILE) {
			statement = whileStat();
		} else {
			statement = assignExprStat();
		}

		return statement;

	}

	private AssignmentExpressionStatement assignExprStat() {

		AssignmentExpressionStatement assignmentExpressionStatement = null;
		Expression leftExpression = null;
		Expression rightExpression = null;

		leftExpression = expr();

		if (lexer.token == Symbol.ASSIGN) {

			lexer.nextToken();
			
			rightExpression = expr();
			
			//TODO Verifica se a expressão a esquerda é id
			
			
			//Verificação de tipos		
			try {
				if( leftExpression.getType() == null) {
					error.signal("Expressão à esquerda não tem tipo associado.");
				}
				
				if( rightExpression.getType() == null) {
					error.signal("Expressão à direita não tem tipo associado.");
				}
				
				if( leftExpression.getType() != null && rightExpression.getType() != null)
					if( ! leftExpression.getType().getClass().equals(rightExpression.getType().getClass()) ) {
					error.signal("Tipos das expressões são incompatíveis: " + leftExpression.getType().getName() + " e " + rightExpression.getType().getName() + ".");
				}
			}catch( NullPointerException e ) {	
				error.signal("Não foi possível verificar os tipos das expressões.");
			}
			
			assignmentExpressionStatement = new AssignmentExpressionStatement(leftExpression, rightExpression);
		} else {
			
			//TODO verificar se é uma função com retorno, se for, erro
			
		}

		if (lexer.token == Symbol.SEMICOLON) {

			lexer.nextToken();
			if (assignmentExpressionStatement == null) {
				assignmentExpressionStatement = new AssignmentExpressionStatement(leftExpression);
			}
		} else {
			error.signal("Esperado o token \";\".");
		}

		return assignmentExpressionStatement;

	}

	private ReturnStatement returnStat() {

		ReturnStatement returnStatement = null;
		Expression expression = null;

		if (lexer.token == Symbol.RETURN) {

			lexer.nextToken();
			expression = expr();

			if (lexer.token == Symbol.SEMICOLON) {
				returnStatement = new ReturnStatement(expression);

				lexer.nextToken();
			} else {
				error.signal("Esperado o token \";\".");
			}
		} else {
			error.signal("Esperado o token \"return\".");
		}

		return returnStatement;
	}

	private VariableDeclarationStatement varDecStat() {

		VariableDeclarationStatement variableDeclarationStatement = null;

		if (lexer.token == Symbol.VAR) {

			lexer.nextToken();

			Identifier identifier = id();

			if (symbolTable.has(identifier)) {
				error.signal(identifier.getName() + " redeclarado");
			}

			symbolTable.put(identifier); // Adiciona o identificador sem a função ainda, para evitar que um
											// identificador com o mesmo nome seja declarado

			if (lexer.token == Symbol.COLON) {

				lexer.nextToken();

				Type type = type();

				if (lexer.token == Symbol.SEMICOLON) {

					Variable variable = new Variable(identifier, type);

					variableDeclarationStatement = new VariableDeclarationStatement(variable);

					symbolTable.put(identifier);

					lexer.nextToken();
				} else {
					error.signal("Esperado o token \";\".");
				}
			} else {
				error.signal("Esperado o token \":\".");
			}
		} else {
			error.signal("Esperado o token \"var\".");
		}

		return variableDeclarationStatement;
	}

	private IfStatement ifStat() {

		IfStatement ifStatement = null;
		Expression expression = null;
		List<Statement> statementsTrue = null;
		List<Statement> statementsFalse = null;

		if (lexer.token == Symbol.IF) {

			lexer.nextToken();

			expression = expr();
			statementsTrue = statList();

			if (lexer.token == Symbol.ELSE) {

				lexer.nextToken();
				statementsFalse = statList();

				ifStatement = new IfStatement(expression, statementsTrue, statementsFalse);
			}
		} else {
			error.signal("Esperado o token \"if\".");
		}

		return ifStatement;
	}

	private WhileStatement whileStat() {

		WhileStatement whileStatement = null;

		if (lexer.token == Symbol.WHILE) {

			lexer.nextToken();

			Expression expression = expr();
			List<Statement> statementList = statList();

			whileStatement = new WhileStatement(expression, statementList);

		} else {
			error.signal("Esperado o token \"while\".");
		}

		return whileStatement;

	}

	private Expression expr() {

		List<ExpressionAnd> expressionAndList = new ArrayList<ExpressionAnd>();

		do {
			if (lexer.token == Symbol.OR) {

				lexer.nextToken();
			}
			expressionAndList.add(exprAnd());
		} while (lexer.token == Symbol.OR);

		return new Expression(expressionAndList);
	}

	private ExpressionAnd exprAnd() {

		List<ExpressionRelational> expressionRelationalList = new ArrayList<ExpressionRelational>();

		do {
			if (lexer.token == Symbol.AND) {

				lexer.nextToken();
			}
			expressionRelationalList.add(exprRel());
		} while (lexer.token == Symbol.AND);

		return new ExpressionAnd(expressionRelationalList);
	}

	private ExpressionRelational exprRel() {

		ExpressionAddition expressionAddLeft = exprAdd();

		if (lexer.token == Symbol.GREATER || lexer.token == Symbol.GREATER_OR_EQUAL || lexer.token == Symbol.LESS
				|| lexer.token == Symbol.LESS_OR_EQUAL || lexer.token == Symbol.EQUAL
				|| lexer.token == Symbol.NOT_EQUAL) {

			RelationalOperator relationalOperator = relOp();
			ExpressionAddition expressionAddRight = exprAdd();

			return new ExpressionRelational(expressionAddLeft, relationalOperator, expressionAddRight);

		} else {
			return new ExpressionRelational(expressionAddLeft);
		}

	}

	private RelationalOperator relOp() {

		RelationalOperator relationalOperator = null;

		switch (lexer.token) {
		case LESS:
			relationalOperator = lessOperator;
			break;

		case LESS_OR_EQUAL:
			relationalOperator = lessOrEqualOperator;
			break;

		case GREATER:
			relationalOperator = greaterOperator;
			break;

		case GREATER_OR_EQUAL:
			relationalOperator = greaterOrEqualOperator;
			break;

		case EQUAL:
			relationalOperator = equalOperator;
			break;

		case NOT_EQUAL:
			relationalOperator = notEqualOperator;
			break;

		default:
			error.signal("Esperado o token relacional (\"<\", \"<=\", \">\", \">=\", \"==\", \"!=\")");
			break;
		}

		lexer.nextToken();

		return relationalOperator;

	}

	private ExpressionAddition exprAdd() {

		ExpressionAddition expressionAddition = new ExpressionAddition(exprMult());

		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {

			Symbol operator = lexer.nextToken();
			expressionAddition.addOperatorExpressionMultiplication(operator, exprMult());
		}

		return expressionAddition;

	}

	private ExpressionMultiplication exprMult() {

		ExpressionMultiplication expressionMultiplication = new ExpressionMultiplication(exprUnary());

		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {
			Symbol operator = lexer.nextToken();
			expressionMultiplication.addOperatorExpressionUnary(operator, exprUnary());
		}

		return expressionMultiplication;

	}

	private ExpressionUnary exprUnary() {

		Symbol operator = null;

		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			operator = lexer.token;
			lexer.nextToken();
		}

		ExpressionPrimary expressionPrimary = exprPrimary();

		if (operator == null) {
			return new ExpressionUnary(expressionPrimary);
		} else {
			return new ExpressionUnary(operator, expressionPrimary);
		}

	}

	private ExpressionPrimary exprPrimary() {

		ExpressionPrimary expressionPrimary = null;

		if (lexer.token == Symbol.IDENTIFIER) {

			Identifier identifier = id();

			if (!symbolTable.has(identifier)) {
				error.signal(identifier.getName() + " não foi declarada");
			}

			Identifiable identifiable = symbolTable.get(identifier).getIdentifiable();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				if (identifiable.getClass() != Function.class) {
					error.signal(identifier.getName() + " não é uma função");
				}
				expressionPrimary = funcCall((Function) identifiable);
			} else {
				if (identifiable.getClass() != Variable.class) {
					error.signal(identifier.getName() + " não é uma variável");
				}
				expressionPrimary = identifier;
			}

		} else {
			expressionPrimary = exprLiteral();
		}

		return expressionPrimary;
	}

	private ExpressionLiteral exprLiteral() {

		ExpressionLiteral expressionLiteral = null;
		Literal literal = null;

		switch (lexer.token) {
		case WORD:
			literal = literalString();
			break;

		case NUMBER:
			literal = literalInt();
			break;

		case TRUE:
		case FALSE:
			literal = literalBoolean();
			break;

		default:
			error.signal("Esperado uma expressão.");
			break;
		}

		if (literal != null) {
			expressionLiteral = new ExpressionLiteral(literal);
		}

		return expressionLiteral;
	}

	private LiteralBoolean literalBoolean() {

		LiteralBoolean literalBoolean = null;

		switch (lexer.token) {
		case TRUE:
			literalBoolean = new LiteralBoolean(true);

			lexer.nextToken();
			break;

		case FALSE:
			literalBoolean = new LiteralBoolean(false);

			lexer.nextToken();
			break;
		default:
			error.signal("Esperado um valor booleano(\"true\" ou \"false\").");
			break;
		}

		return literalBoolean;

	}

	private LiteralInt literalInt() {

		LiteralInt literalInt = null;

		if (lexer.token == Symbol.NUMBER) {
			literalInt = new LiteralInt(lexer.numberValue);

			lexer.nextToken();
		} else {
			error.signal("Esperado um número inteiro.");
		}

		return literalInt;

	}

	private LiteralString literalString() {

		LiteralString literalString = null;

		if (lexer.token == Symbol.WORD) {
			literalString = new LiteralString(lexer.stringValue);

			lexer.nextToken();
		} else {
			error.signal("Esperado uma string.");
		}

		return literalString;

	}

	private ExpressionFunctionCall funcCall(Function function) {

		// Recebe o Function da chamada anterior

		ExpressionFunctionCall expressionFunctionCall = new ExpressionFunctionCall(function.getIdentifier());

		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {

				lexer.nextToken();
				expressionFunctionCall = new ExpressionFunctionCall(function.getIdentifier());

			} else {

				expressionFunctionCall = new ExpressionFunctionCall(function.getIdentifier(), expr());

				while (lexer.token == Symbol.COMMA) {
					lexer.nextToken();
					expressionFunctionCall.addExpression(expr());
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {

					lexer.nextToken();
				} else {
					error.signalWrongToken(Symbol.RIGHT_PARENTHESIS);
				}

				if (function.getIdentifier().getName() == "write") {
					// Caso especial para write()
					error.signal("Exceções para write ainda não definidas");
				} else if (function.getIdentifier().getName() == "writeln") {
					// Caso especial para writeln()
					error.signal("Exceções para writeln ainda não definidas");
				} else {
					if (function.getParamListSize() != expressionFunctionCall.getSize()) {
						error.signal("Quantidade de parâmetros incorreta.");
					}
				}
			}
		} else {
			error.signalWrongToken(Symbol.LEFT_PARENTHESIS);
		}

		return expressionFunctionCall;
	}

	private Identifier id() {

		Identifier identifier = null;

		if (lexer.token == Symbol.IDENTIFIER) {
			//Procura identificador na SymbolTable
			identifier = this.symbolTable.get(lexer.stringValue);
			
			//Se não achou, cria um novo
			if(identifier == null){
				identifier = new Identifier(lexer.stringValue);
			}
			
			lexer.nextToken();
		} else {
			error.signal("Esperado um identificador.");
		}

		return identifier;

	}

}
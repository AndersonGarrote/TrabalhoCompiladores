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

	public Compiler() {
	}

	public Program compile(char[] p_input, PrintWriter printWriter) {
		input = p_input;
		outError = printWriter;
		error = new CompilerError(lexer, outError);
		lexer = new Lexer(input, error);
		error.setLexer(lexer);

		lexer.nextToken();
		return program();
	}

	public Program program() {

		// System.out.println("Program");

		List<Function> functionList = new ArrayList<Function>();

		functionList.add(func());

		while (lexer.token != Symbol.EOF) {
			functionList.add(func());
		}

		return new Program(functionList);
	}

	public Function func() {

		Function function = null;
		Identifier id = null;
		ParameterList parameterList = null;
		Type ret = null;
		StatementList statementList = null;

		if (lexer.token == Symbol.FUNCTION) {

			lexer.nextToken();

			id = id();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {

				lexer.nextToken();

				parameterList = paramList();

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {

					lexer.nextToken();
				} else {
					error.signal("Esperado token \")\".");
				}

			}
			if (lexer.token == Symbol.ARROW) {

				lexer.nextToken();
				ret = type();
			}

			statementList = statList();

			function = new Function(id, parameterList, ret, statementList);

		} else {
			error.signal("Esperado o token \"function\".");
		}

		return function;
	}

	public ParameterList paramList() {

		List<Parameter> parameters = new ArrayList<Parameter>();

		do {
			if (lexer.token == Symbol.COMMA) {

				lexer.nextToken();
			}
			parameters.add(paramDec());
		} while (lexer.token == Symbol.COMMA);

		return new ParameterList(parameters);

	}

	public Parameter paramDec() {
		Identifier identifier = id();
		if (lexer.token == Symbol.COLON) {

			lexer.nextToken();
			Type type = type();
			return new Parameter(identifier, type);
		} else {
			error.signal("Esperado o token \":\".");
			return null;
		}
	}

	public Type type() {

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

	public StatementList statList() {

		StatementList statementList = new StatementList();

		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {

			lexer.nextToken();

			while (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
				statementList.addStatement(stat());
			}

			lexer.nextToken();

			return statementList;

		} else {
			error.signal("Esperado o token \"{\"");
			return null;
		}

	}

	public Statement stat() {

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

	public AssignmentExpressionStatement assignExprStat() {

		AssignmentExpressionStatement assignmentExpressionStatement = null;
		Expression leftExpression = null;
		Expression rightExpression = null;

		leftExpression = expr();

		if (lexer.token == Symbol.ASSIGN) {

			lexer.nextToken();
			rightExpression = expr();
			assignmentExpressionStatement = new AssignmentExpressionStatement(leftExpression, rightExpression);
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

	public ReturnStatement returnStat() {

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

	public VariableDeclarationStatement varDecStat() {

		VariableDeclarationStatement variableDeclarationStatement = null;

		if (lexer.token == Symbol.VAR) {

			lexer.nextToken();

			Identifier identifier = id();

			if (lexer.token == Symbol.COLON) {

				lexer.nextToken();

				Type type = type();

				if (lexer.token == Symbol.SEMICOLON) {
					variableDeclarationStatement = new VariableDeclarationStatement(identifier, type);

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

	public IfStatement ifStat() {

		IfStatement ifStatement = null;
		Expression expression = null;
		StatementList statementsTrue = null;
		StatementList statementsFalse = null;

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

	public WhileStatement whileStat() {

		WhileStatement whileStatement = null;

		if (lexer.token == Symbol.WHILE) {

			lexer.nextToken();

			Expression expression = expr();
			StatementList statementList = statList();

			whileStatement = new WhileStatement(expression, statementList);

		} else {
			error.signal("Esperado o token \"while\".");
		}

		return whileStatement;

	}

	public Expression expr() {

		List<ExpressionAnd> expressionAndList = new ArrayList<ExpressionAnd>();

		do {
			if (lexer.token == Symbol.OR) {

				lexer.nextToken();
			}
			expressionAndList.add(exprAnd());
		} while (lexer.token == Symbol.OR);

		return new Expression(expressionAndList);
	}

	public ExpressionAnd exprAnd() {

		List<ExpressionRelational> expressionRelationalList = new ArrayList<ExpressionRelational>();

		do {
			if (lexer.token == Symbol.AND) {

				lexer.nextToken();
			}
			expressionRelationalList.add(exprRel());
		} while (lexer.token == Symbol.AND);

		return new ExpressionAnd(expressionRelationalList);
	}

	public ExpressionRelational exprRel() {

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

	public RelationalOperator relOp() {

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

	public ExpressionAddition exprAdd() {

		ExpressionAddition expressionAddition = new ExpressionAddition(exprMult());

		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {

			Symbol operator = lexer.nextToken();
			expressionAddition.addOperatorExpressionMultiplication(operator, exprMult());
		}

		return expressionAddition;

	}

	public ExpressionMultiplication exprMult() {

		ExpressionMultiplication expressionMultiplication = new ExpressionMultiplication(exprUnary());

		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {

			Symbol operator = lexer.nextToken();
			expressionMultiplication.addOperatorExpressionUnary(operator, exprUnary());
		}

		return expressionMultiplication;

	}

	public ExpressionUnary exprUnary() {

		Symbol operator = Symbol.PLUS;

		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			operator = lexer.token;

			lexer.nextToken();
		}

		ExpressionPrimary expressionPrimary = exprPrimary();

		return new ExpressionUnary(operator, expressionPrimary);
	}

	public ExpressionPrimary exprPrimary() {

		ExpressionPrimary expressionPrimary = null;

		if (lexer.token == Symbol.WRITE) {

			lexer.nextToken();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				expressionPrimary = funcCall(new Identifier("write"));
			}
		} else if (lexer.token == Symbol.WRITELN) {

			lexer.nextToken();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				expressionPrimary = funcCall(new Identifier("writeLn"));
			}
		} else if (lexer.token == Symbol.IDENTIFIER) {

			Identifier identifier = id();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				expressionPrimary = funcCall(identifier);
			} else {
				expressionPrimary = identifier;
			}

		} else {
			expressionPrimary = exprLiteral();
		}

		return expressionPrimary;
	}

	public ExpressionLiteral exprLiteral() {

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
			error.signal("Esperado uma expressão literal.");
			break;
		}

		if (literal != null) {
			expressionLiteral = new ExpressionLiteral(literal);
		}

		return expressionLiteral;
	}

	public LiteralBoolean literalBoolean() {

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

	public LiteralInt literalInt() {

		LiteralInt literalInt = null;

		if (lexer.token == Symbol.NUMBER) {
			literalInt = new LiteralInt(lexer.numberValue);

			lexer.nextToken();
		} else {
			error.signal("Esperado um número inteiro.");
		}

		return literalInt;

	}

	public LiteralString literalString() {

		LiteralString literalString = null;

		if (lexer.token == Symbol.WORD) {
			literalString = new LiteralString(lexer.stringValue);

			lexer.nextToken();
		} else {
			error.signal("Esperado uma string.");
		}

		return literalString;

	}

	public ExpressionFunctionCall funcCall(Identifier identifier) {

		// Recebe o Identifier da chamada anterior

		ExpressionFunctionCall expressionFunctionCall = null;

		if (lexer.token == Symbol.LEFT_PARENTHESIS) {

			lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {

				lexer.nextToken();

			} else {

				expressionFunctionCall = new ExpressionFunctionCall(identifier, expr());

				while (lexer.token == Symbol.COMMA) {

					lexer.nextToken();
					expressionFunctionCall.addExpression(expr());
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {

					lexer.nextToken();
				} else {
					error.signal("Esperado o token \")\".");
				}
			}
		} else {
			error.signal("Esperado o token \"(\".");
		}

		return expressionFunctionCall;
	}

	public Identifier id() {

		Identifier identifier = null;

		if (lexer.token == Symbol.IDENTIFIER) {
			identifier = new Identifier(lexer.stringValue);

			lexer.nextToken();
		} else {
			error.signal("Esperado um identificador.");
		}

		return identifier;

	}

}
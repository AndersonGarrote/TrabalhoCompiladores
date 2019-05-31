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
		System.out.println(lexer.token);
		lexer.nextToken();
		return program();
	}

	public Program program() {

		System.out.println("Program");

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
			System.out.println(lexer.token);
			lexer.nextToken();

			id = id();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				System.out.println(lexer.token);
				lexer.nextToken();

				parameterList = paramList();

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token);
					lexer.nextToken();
				} else {
					error.signal("Esperado token \")\".");
				}

			}
			if (lexer.token == Symbol.ARROW) {
				System.out.println(lexer.token);
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
				System.out.println(lexer.token);
				lexer.nextToken();
			}
			parameters.add(paramDec());
		} while (lexer.token == Symbol.COMMA);

		return new ParameterList(parameters);

	}

	public Parameter paramDec() {
		Identifier identifier = id();
		if (lexer.token == Symbol.COLON) {
			System.out.println(lexer.token);
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
			type = new BooleanType(lexer.stringValue);
			break;
		case INTEGER:
			type = new IntegerType(lexer.stringValue);
			break;
		case STRING:
			type = new StringType(lexer.stringValue);
			break;
		default:
			error.signal("Esperado token de tipo (\"Int\", \"Boolean\" ou \"String\").");
			return null;
		}

		System.out.println(lexer.token);

		lexer.nextToken();

		return type;

	}

	public StatementList statList() {

		List<Statement> statements = new ArrayList<Statement>();

		if (lexer.token == Symbol.LEFT_CURLY_BRACKET) {

			do {
				if (lexer.token != Symbol.RIGHT_CURLY_BRACKET) {
					System.out.println(lexer.token);
					lexer.nextToken();
				}
				statements.add(stat());
			} while (lexer.token != Symbol.RIGHT_CURLY_BRACKET);

			System.out.println(lexer.token);
			lexer.nextToken();

			return new StatementList(statements);

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
			System.out.println(lexer.token);
			lexer.nextToken();
			rightExpression = expr();
			assignmentExpressionStatement = new AssignmentExpressionStatement(leftExpression, rightExpression);
		}
		if (lexer.token == Symbol.SEMICOLON) {
			System.out.println(lexer.token);
			lexer.nextToken();
			assignmentExpressionStatement = new AssignmentExpressionStatement(leftExpression);
		} else {
			error.signal("Esperado o token \";\".");
		}

		return assignmentExpressionStatement;

	}

	public ReturnStatement returnStat() {

		ReturnStatement returnStatement = null;
		Expression expression = null;

		if (lexer.token == Symbol.RETURN) {
			System.out.println(lexer.token);
			lexer.nextToken();
			expression = expr();

			if (lexer.token == Symbol.SEMICOLON) {
				returnStatement = new ReturnStatement(expression);
				System.out.println(lexer.token);
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
			System.out.println(lexer.token);
			lexer.nextToken();

			Identifier identifier = id();

			if (lexer.token == Symbol.COLON) {

				System.out.println(lexer.token);

				lexer.nextToken();

				Type type = type();

				if (lexer.token == Symbol.SEMICOLON) {
					variableDeclarationStatement = new VariableDeclarationStatement(identifier, type);
					System.out.println(lexer.token);
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
			System.out.println(lexer.token);
			lexer.nextToken();

			expression = expr();
			statementsTrue = statList();

			if (lexer.token == Symbol.ELSE) {
				System.out.println(lexer.token);
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
			System.out.println(lexer.token);
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
				System.out.println(lexer.token);
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
				System.out.println(lexer.token);
				lexer.nextToken();
			}
			expressionRelationalList.add(exprRel());
		} while (lexer.token == Symbol.AND);

		return new ExpressionAnd(expressionRelationalList);
	}

	public ExpressionRelational exprRel() {

		ExpressionAddition expressionAddLeft = exprAdd();
		RelationalOperator relationalOperator = null;
		ExpressionAddition expressionAddRight = null;

		if (lexer.token == Symbol.GREATER || lexer.token == Symbol.GREATER_OR_EQUAL || lexer.token == Symbol.LESS
				|| lexer.token == Symbol.LESS_OR_EQUAL || lexer.token == Symbol.EQUAL
				|| lexer.token == Symbol.NOT_EQUAL) {
			relationalOperator = relOp();
			expressionAddRight = exprAdd();
		}

		return new ExpressionRelational(expressionAddLeft, relationalOperator, expressionAddRight);

	}

	public RelationalOperator relOp() {

		RelationalOperator relationalOperator = null;

		switch (lexer.token) {
		case LESS:
			relationalOperator = new LessOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case LESS_OR_EQUAL:
			relationalOperator = new LessOrEqualOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case GREATER:
			relationalOperator = new GreaterOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case GREATER_OR_EQUAL:
			relationalOperator = new GreaterOrEqualOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case EQUAL:
			relationalOperator = new EqualOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case NOT_EQUAL:
			relationalOperator = new NotEqualOperator();
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		default:
			error.signal("Esperado o token relacional (\"<\", \"<=\", \">\", \">=\", \"==\", \"!=\")");
			break;
		}

		return relationalOperator;

	}

	public ExpressionAddition exprAdd() {

		ExpressionAddition expressionAddition = new ExpressionAddition(exprMult());

		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			System.out.println(lexer.token);
			Symbol operator = lexer.nextToken();
			expressionAddition.addOperatorExpressionMultiplication(operator, exprMult());
		}

		return expressionAddition;

	}

	public ExpressionMultiplication exprMult() {

		ExpressionMultiplication expressionMultiplication = new ExpressionMultiplication(exprUnary());

		while (lexer.token == Symbol.TIMES || lexer.token == Symbol.DIVISION) {
			System.out.println(lexer.token);
			Symbol operator = lexer.nextToken();
			expressionMultiplication.addOperatorExpressionUnary(operator, exprUnary());
		}

		return expressionMultiplication;

	}

	public ExpressionUnary exprUnary() {

		Symbol operator = Symbol.PLUS;

		if (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			operator = lexer.token;
			System.out.println(lexer.token);
			lexer.nextToken();
		}

		ExpressionPrimary expressionPrimary = exprPrimary();

		return new ExpressionUnary(operator, expressionPrimary);
	}

	public ExpressionPrimary exprPrimary() {

		ExpressionPrimary expressionPrimary = null;

		if (lexer.token == Symbol.WRITE) {
			System.out.println(lexer.token);
			lexer.nextToken();

			if (lexer.token == Symbol.LEFT_PARENTHESIS) {
				expressionPrimary = funcCall(new Identifier("write"));
			}
		} else if (lexer.token == Symbol.WRITELN) {
			System.out.println(lexer.token);
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
			System.out.println(lexer.token);
			lexer.nextToken();
			break;

		case FALSE:
			literalBoolean = new LiteralBoolean(false);
			System.out.println(lexer.token);
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
			System.out.println(lexer.token);
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
			System.out.println(lexer.token);
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

			System.out.println(lexer.token);
			lexer.nextToken();

			if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
				System.out.println(lexer.token);
				lexer.nextToken();

			} else {

				expressionFunctionCall = new ExpressionFunctionCall(identifier, expr());

				while (lexer.token == Symbol.COMMA) {
					System.out.println(lexer.token);
					lexer.nextToken();
					expressionFunctionCall.addExpression(expr());
				}

				if (lexer.token == Symbol.RIGHT_PARENTHESIS) {
					System.out.println(lexer.token);
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
			System.out.println(lexer.token);
			lexer.nextToken();
		} else {
			error.signal("Esperado um identificador.");
		}

		return identifier;

	}

}

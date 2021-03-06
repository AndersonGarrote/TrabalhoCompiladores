/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
package Lexer;

public enum Symbol {

    FUNCTION("function"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    ARROW("->"),
    COMMA(","),
    IDENTIFIER("Identifier"),
    COLON(":"),
    INTEGER("Int"),
    BOOLEAN("Boolean"),
    STRING("String"),
    LEFT_CURLY_BRACKET("{"),
    RIGHT_CURLY_BRACKET("}"),
    ASSIGN("="),
    SEMICOLON(";"),
    RETURN("return"),
    VAR("var"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    OR("or"),
    AND("and"),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
    NOT_EQUAL("!="),
    EQUAL("=="),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVISION("/"),
    TRUE("true"),
    FALSE("false"),
    NUMBER("Number"),
    WORD("Word"),
	EOF("eof");

    Symbol(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    String name;

}
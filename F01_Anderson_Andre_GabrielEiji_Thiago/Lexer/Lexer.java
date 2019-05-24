package Lexer;

public class Lexer {

    public Symbol token;
    
    
    public void nextToken() {
    	char ch;
    	
    	while( (ch = input[tokenPos]) == ' ' || ch == '\r' || 
    			ch == '\t' || ch == '\n') {
    		// conta o número de linhas
    		
    		if(ch == '\n') {
    			lineNumber++;
    		}
    		tokenPos++;
    	}
    	if(ch == '\0') {
    		token = Symbol.EOF;
    	} else {
    		if(input[tokenPos] == '/' && input[tokenPos+1] == '/') {
    			//Comentário encontrado
    			while (input[tokenPos] != '\0' && input[tokenPos] != '\n') {
    				tokenPos++;
    			}
    			nextToken();
    		}
    		else {
    			if(Character.isLetter(ch)) {
    				StringBuffer ident = new StringBuffer();
    				while(character.isLetter(input[tokenPos])) {
    					ident.append(input[tokenPos]);
    					tokenPos++;
    				}
    				StringValue = ident.toString();
    				Symbol value = keywordsTable.get(StringValue);
    				if(value == null) {
    					token = Symbol.IDENTIFIER;
    				} else {
    					token = value;
    				}
    				if(Character.isDigit(input[tokenPos])) {
    					error.signal("Word followed by a number");
    				}
    			} else if(Character.isDigit(ch)) {
    				StringBuffer number = new StringBuffer();
    				while(Character.isDigit(input[tokenPos])) {
    					number.append(input[tokenPos]);
    					tokenPos++;
    				}
    				try {
    					numberValue = Integer.valueOf(number.toString()).intValue();
    				} catch (NumberFormatException e) {
    					error.signal("Number out of limits");
    				}
    				if(number >= MaxValueInteger) {
    					error.signal("Number out of limits");
    				}
    			} else {
    				tokenPos++;
    				switch(ch) {
    					case '+':
    						token = Symbol.PLUS;
    						break;
    					
    					case '-':
    						token = Symbol.MINUS;
    						break;
    					
    					case '*':
    						token = Symbol.TIMES;
    						break;
    					
    					case '/':
    						token = Symbol.DIVISION;
    						break;
    					
    					case '<':
    						if(input[tokenPos] == '=') {
    							tokenPos++;
    							token = Symbol.LESS_OR_EQUAL;
    						} else {
    							token = Symbol.LESS;
    						}
    						break;
    					
    					case '>':
    						if(input[tokenPos] == '=') {
    							tokenPos++;
    							token = Symbol.GREATER_OR_EQUAL;
    						} else {
    							token = Symbol.GREATER;
    						}
    						break;
    					
    					case '=':
    						if(input[tokenPos] == '=') {
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
    						token = Symbol.WORD;
    						charValue = input[tokenPos];
    						tokenPos++;
    						if(input[tokenPos] != '\"') {
    							error.signal("Illegal literal character" + input[tokenPos-1]);
    						}
    						tokenPos++;
    						break;
    						
    					default:
    						error.signal("invalid Character: '" + ch + "'");
    				}
    			}
    		}
    		lastTokenPos = tokenPos-1;
    	}
    }
}
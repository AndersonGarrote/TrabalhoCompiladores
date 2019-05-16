import java.io.PrintWriter;

import AST.Program;

public class Compiler {

	char token;

	public Program compile(char[] input, PrintWriter printWriter) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Program() {
		Func();
		while(token != EOF)
			Func();
	}

	public void Func() {
		if(token == 'F') {
			Id();
			if(token = '(') {
				nextToken();
				ParamList();
				
				if(token == ')') {
					nextToken();
				}
				else error();
			}
			if(token == '-') {
				nextToken();
				if(token == '>')
				{
					nextToken();
					Type();
				}
				else error();
			}

			StateList();
		} else error();
	}

	public void ParamList() {
		ParamDec();

		while(token != ',')
			ParamDec();
	}

	public void ParamDec() {
		Id();
		if(token == ':')
		{
			nextToken();
			Type();
		}
		else error();
	}

	public void Type() {
		if(token != 'I' && token != 'B' && token != 'S')
			error();
	}
	 
	public void StatList() {
		if(token == '{') {
			while(token != '}')
				Stat();
		}
	}

	public void Stat() {
		
	}
}

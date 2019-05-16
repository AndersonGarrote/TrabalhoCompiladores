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

	public void AssingExprStat() {

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

	}

	public void ExprAdd() {

	}

	public void ExprMult() {

	}

	public void ExprUnary() {

	}

	public void ExprPrimary() {

	}

	public void ExprLiteral() {

	}

	public void LiteralBoolean() {

	}

	public void FuncCall() {
		
	}
}

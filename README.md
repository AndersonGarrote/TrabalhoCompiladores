# TrabalhoCompiladores

Observações:
    Na expressão regular, o uso de {x} significa uma repetição de x zero ou mais vezes
    O uso de [x] significa que x pode não ocorrer mas caso ocorra, ocorrerá apenas uma vez
    O uso de (x) significa que ocorre antes de fazer o resto da expressão, ex: (x|y)z significa que aceita:
        xz ou yz

Grámática:

Program ::= Func {Func}
Func ::= "function" Id [ "(" ParamList ")" ] [ "->" Type ] StatList  
Usaremos "function" = "F"  
ParamList ::= ParamDec { ”, ” ParamDec }  
ParamDec ::= Id ":" Type  
Type ::= "Int" | "Boolean" | "String"  
Usaremos "Int" = "I", "Boolean" = "B" e "String" = "S"  
StatList ::= "{” {Stat} ”}"  
Stat ::= AssignExprStat | ReturnStat | VarDecStat | IfStat | WhileStat  
AssignExprStat ::= Expr [ "=" Expr ] ";"  

ReturnStat ::= "return" Expr ";"  
Usaremos "return" = "R"  
VarDecStat ::= "var" Id ":" Type ";"  
Usaremos "var" = "V"  
IfStat ::= "if" Expr StatList [ "else" StatList ]  
Usaremos "if" = "F" e "else" = "E"  
WhileStat ::= "while" Expr StatList  
Usaremos "while" = "W"
Expr ::= ExprAnd { ”or” ExprAnd}  
Usaremos "or" = "O"  
ExprAnd ::= ExprRel { ”and” ExprRel}  
Usaremos "and" = "A"  
ExprRel ::= ExprAdd [ RelOp ExprAdd ]  

RelOp ::= "<" | "<=" | ">" | ">=" | "==" | "!="  
Usaremos apenas "<" e ">"
ExprAdd ::= ExprMult { ( ”+” | ”−” ) ExprMult }  
ExprMult ::= ExprUnary { ( ”∗” | ”/” ) ExprUnary }  
ExprUnary ::= [ ( "+" | "-" ) ] ExprPrimary  
ExprPrimary ::= Id | FuncCall | ExprLiteral  
ExprLiteral ::= LiteralInt | LiteralBoolean | LiteralString  
LiteralBoolean ::= "true" | "false"  
Usaremos "true" = "t" | "false" = "f"  
FuncCall ::= Id "(" [ Expr {”, ” Expr } ] ")"  

# TrabalhoCompiladores

Observações:
    Na expressão regular, o uso de {x} significa uma repetição de x zero ou mais vezes
    O uso de [x] significa que x pode não ocorrer mas caso ocorra, ocorrerá apenas uma vez
    O uso de (x) significa que ocorre antes de fazer o resto da expressão, ex: (x|y)z significa que aceita:
        xz ou yz

Grámática:

Program ::= Func {Func}
Func ::= "function" Id [ "(" ParamList ")" ] [ "->" Type ] StatList
ParamList ::= ParamDec { ”, ” ParamDec }
ParamDec ::= Id ":" Type
Type ::= "Int" | "Boolean" | "String"
StatList ::= "{” {Stat} ”}"
Stat ::= AssignExprStat | ReturnStat | VarDecStat | IfStat | WhileStat
AssignExprStat ::= Expr [ "=" Expr ] ";"
ReturnStat ::= "return" Expr ";"
VarDecStat ::= "var" Id ":" Type ";"
IfStat ::= "if" Expr StatList [ "else" StatList ]
WhileStat ::= "while" Expr StatList
Expr ::= ExprAnd { ”or” ExprAnd}
ExprAnd ::= ExprRel { ”and” ExprRel}
ExprRel ::= ExprAdd [ RelOp ExprAdd ]
RelOp ::= "<" | "<=" | ">" | ">=" | "==" | "!="
ExprAdd ::= ExprMult { ( ”+” | ”−” ) ExprMult }
ExprMult ::= ExprUnary { ( ”∗” | ”/” ) ExprUnary }
ExprUnary ::= [ ( "+" | "-" ) ] ExprPrimary
ExprPrimary ::= Id | FuncCall | ExprLiteral
ExprLiteral ::= LiteralInt | LiteralBoolean | LiteralString
LiteralBoolean ::= "true" | "false"
FuncCall ::= Id "(" [ Expr {”, ” Expr } ] ")"
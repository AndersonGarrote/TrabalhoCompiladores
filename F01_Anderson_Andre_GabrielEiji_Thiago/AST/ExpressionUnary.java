package AST;

class ExpressionUnary implements Printable {

    private char sinal;

    ExpressionUnary(char sinal) {
        this.sinal = sinal;
    }
    
    @Override
    public void genC(PW pw) {

    }

}
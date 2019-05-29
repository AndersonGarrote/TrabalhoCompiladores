package AST;

class ExpressionUnary implements Printable {

    private char sign;

    ExpressionUnary(char sign) {
        this.sign = sign;
    }
    
    @Override
    public void genC(PW pw) {

    }

}
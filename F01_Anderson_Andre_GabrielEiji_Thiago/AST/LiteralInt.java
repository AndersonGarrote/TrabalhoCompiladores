package AST;

public class LiteralInt extends Literal implements Printable{

    private static final int MAX_VALUE = 2147483647;
    private static final int MIN_VALUE = 0;

    private int value;

    public LiteralInt(int value) throws IllegalArgumentException {

        if(value > MAX_VALUE) {
            throw new IllegalArgumentException("Integer underflow");
        }

        if(value < MIN_VALUE) {
            throw new IllegalArgumentException("Integer overflow");
        }

        this.value = value;
        
    }

    public void genC(PW pw) {
        pw.out.print(this.value);
    }

}
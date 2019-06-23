/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/
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
        this.type = Type.integerType;
        
    }

    public void genC(PW pw) {
        pw.out.print(this.value);
    }

}
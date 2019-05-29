package AST;

public class EqualOperator extends RelationalOperator{

    public EqualOperator() {
        super("==");
    }

    @Override
    public String getCname() {
        return "==";
    }

}
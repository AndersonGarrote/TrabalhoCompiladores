package AST;

public class GreaterEqualOperator extends RelationalOperator{

    public GreaterEqualOperator() {
        super(">=");
    }

    @Override
    public String getCname() {
        return ">=";
    }

}
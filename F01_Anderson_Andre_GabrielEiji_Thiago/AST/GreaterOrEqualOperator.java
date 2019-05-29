package AST;

public class GreaterOrEqualOperator extends RelationalOperator{

    public GreaterOrEqualOperator() {
        super(">=");
    }

    @Override
    public String getCname() {
        return ">=";
    }

}
package AST;

public class LessOrEqualOperator extends RelationalOperator {

    public LessOrEqualOperator() {
        super("<=");
    }

    @Override
    public String getCname() {
        return "<=";
    }

}
package AST;

public class LessEqualOperator extends RelationalOperator {

    public LessEqualOperator() {
        super("<=");
    }

    @Override
    public String getCname() {
        return "<=";
    }

}
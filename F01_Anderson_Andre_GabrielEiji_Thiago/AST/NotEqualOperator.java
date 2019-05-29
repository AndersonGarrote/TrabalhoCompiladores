package AST;

public class NotEqualOperator extends RelationalOperator{

    public NotEqualOperator() {
        super("!=");
    }

    @Override
    public String getCname() {
        return "!=";
    }

}
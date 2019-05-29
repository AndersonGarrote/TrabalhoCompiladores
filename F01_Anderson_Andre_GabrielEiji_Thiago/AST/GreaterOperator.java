package AST;

public class GreaterOperator extends RelationalOperator {

    public GreaterOperator() {
        super(">");
    }

    @Override
    public String getCname() {
        return ">";
    }
}
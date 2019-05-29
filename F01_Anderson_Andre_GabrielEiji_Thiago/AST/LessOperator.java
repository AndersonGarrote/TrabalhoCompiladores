package AST;

public class LessOperator extends RelationalOperator{

    public LessOperator() {
        super("<");
    }

    @Override
    public String getCname() {
        return "<";
    }

}
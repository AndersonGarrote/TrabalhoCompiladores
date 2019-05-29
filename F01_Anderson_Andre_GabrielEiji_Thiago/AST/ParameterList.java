package AST;

import java.util.List;

public class ParameterList implements Printable {

    private List<Parameter> parameters;

    public ParameterList(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void genC(PW pw) {

    }

    

}
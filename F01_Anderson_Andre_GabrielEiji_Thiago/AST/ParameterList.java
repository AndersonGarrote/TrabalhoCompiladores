package AST;

import java.util.Iterator;
import java.util.List;

public class ParameterList implements Printable {

    private List<Parameter> parameters;

    public ParameterList(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void genC(PW pw) {

        if(parameters == null) {
            return;
        }

        for (Iterator<Parameter> iterator = parameters.iterator(); iterator.hasNext();) {
            Parameter parameter = iterator.next();
            parameter.genC(pw);
            if(iterator.hasNext()) {
                pw.print(", ");
            }
        }
    }

}
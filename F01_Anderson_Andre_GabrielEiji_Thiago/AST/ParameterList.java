/*
	Anderson Pinheiro Garrote RA: 743505
	Andre Matheus Bariani Trava RA: 743506
	Gabriel Eiji Uema Martin RA: 743536
	Thiago Yussuki Uehara RA:743599
*/

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

        if (parameters == null || parameters.size() == 0) {
            return;
        }

        parameters.get(0).genC(pw);

        parameters.stream().skip(1).forEach(parameter -> {
            pw.print(", ");
            parameter.genC(pw);
        });
        
    }
}
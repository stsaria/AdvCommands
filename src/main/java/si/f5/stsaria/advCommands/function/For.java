package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.NullV;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.Objects;

public class For extends Function {
    @Override
    public String syntax() {
        return "for [A-Za-z] \\d+ .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Variables results = new EmpVariables();
        for (int i = 0; i < Integer.parseInt(codeSplit[2]); i++){
            Function func = Functions.get(codeSplit[3]);
            if (func == null) return new ErrorV("func not found");
            variables.set(codeSplit[1], String.valueOf(i));
            code = Parser.variableSubstitution(variables, code.replaceFirst("for "+codeSplit[1]+" "+codeSplit[2]+" ", ""));
            if (!code.matches(func.syntax())) return new ErrorV("syntax error (content) (i="+i+")");
            Variables r = func.execute(code, variables);
            if (r == null) r = new NullV();
            else if (Objects.equals(r.get("resulttype"), "error")){
                variables.delete(codeSplit[1]);
                return new ErrorV("error in for (i="+i+") -> "+r.get("0"));
            }
            else if (Objects.equals(r.get("resulttype"), "oneresult")) {
                int finalI = i;
                r.getVariableMap().forEach((n, v) -> {
                    if (n.equals("0")) results.set(String.valueOf(finalI), v);
                    else if (n.startsWith("0.")) results.set(finalI+"."+n.replaceFirst("0.", ""), v);
                });
            } else {
                results.concat(String.valueOf(i), r);
            }
            if (Objects.equals(r.get("resulttype"), "oneresult") && Objects.equals(r.get("0"), "break")){
                variables.delete(codeSplit[1]);
                return results;
            }
        }
        variables.delete(codeSplit[1]);
        return results;
    }
}

package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class Split extends Function {
    @Override
    public String syntax() {
        return "split [a-zA-Z0-9.]+ (?s).+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.containsDirect(codeSplit[2])) return new ErrorV("split source variable not found");
        int i = 0;
        Variables result = new EmpVariables();
        for (String c : variables.get(codeSplit[2]).split(code.replaceFirst("strtolist "+codeSplit[1]+" "+codeSplit[2]+" ", ""))){
            result.set("0."+i, c);
            i++;
        }
        result.set("resulttype", "oneresult");
        return result;
    }
}

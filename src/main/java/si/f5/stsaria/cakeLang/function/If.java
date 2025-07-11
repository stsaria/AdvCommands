package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class If extends Function {
    @Override
    public String syntax() {
        return "if (true|false) [a-zA-Z0-9]+ else [a-zA-Z0-9]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].equals("true")){
            Function func = Functions.get(codeSplit[2]);
            if (func == null) return new ErrorV("func not found");
            else if (!codeSplit[1].matches(func.syntax())) return new ErrorV("syntax error (content)");
            return func.execute(codeSplit[1], variables);
        } else if (codeSplit[1].equals("false")){
            Function func = Functions.get(codeSplit[4]);
            if (func == null) return new ErrorV("func not found");
            else if (!codeSplit[4].matches(func.syntax())) return new ErrorV("syntax error (content)");
            return func.execute(codeSplit[4], variables);
        }
        return null;
    }
}

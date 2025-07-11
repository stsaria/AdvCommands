package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.NullV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class TrueIf extends Function {
    @Override
    public String syntax() {
        return "trueif (true|false) .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].equals("true")){
            Function func = Functions.get(codeSplit[2]);
            if (func == null) return new ErrorV("func not found");
            code = code.replaceFirst("trueif true ", "");
            if (!code.matches(func.syntax())) return new ErrorV("syntax error (content)");
            return func.execute(code, variables);
        }
        return new NullV();
    }
}

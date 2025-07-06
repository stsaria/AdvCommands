package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.NullV;
import si.f5.stsaria.advCommands.variables.Variables;

public class TrueIf implements Function{
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
            return func.execute(code, variables.clone());
        }
        return new NullV();
    }
}

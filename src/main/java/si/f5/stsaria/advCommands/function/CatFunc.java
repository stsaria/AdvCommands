package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.Variables;

public class CatFunc extends Function {
    @Override
    public String syntax() {
        return "catfunc [a-zA-Z0-9]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Function func = Functions.get(codeSplit[1]);
        if (!(func instanceof UserFunction)) return new ErrorV("func not found");
        return new ErrorV(((UserFunction) func).formatedLines());
    }
}

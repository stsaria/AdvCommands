package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

public class RunInGlobal extends Function {
    @Override
    public String syntax() {
        return "runinglobal .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        code = Parser.variableSubstitution(variables, code.replaceFirst("runinglobal ", ""));
        Function func = Functions.get(codeSplit[1]);
        if (func == null) return new ErrorV("func not found");
        else if (!code.matches(func.syntax())) return new ErrorV("syntax error (content)");
        return func.execute(code, GlobalVariables.getRaw());
    }
}

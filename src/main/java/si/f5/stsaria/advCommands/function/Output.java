package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class Output implements Function{
    @Override
    public String syntax() {
        return "output [a-zA-Z0-9.]+ .+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Function func = Functions.get(codeSplit[2]);
        if (!(func instanceof UserFunction)) return "error: func not found";
        String r = func.execute(code.replaceFirst("output "+codeSplit[1]+" ", ""));
        GlobalVariables.set(codeSplit[1], r);
        return "";
    }
}

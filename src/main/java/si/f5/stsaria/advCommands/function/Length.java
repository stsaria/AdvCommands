package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class Length implements Function{
    @Override
    public String syntax() {
        return "length [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        GlobalVariables.set(codeSplit[1], String.valueOf(GlobalVariables.length(codeSplit[2])));
        return "";
    }
}

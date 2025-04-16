package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class CopyVar implements Function{
    @Override
    public String syntax() {
        return "copyvarG? [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        GlobalVariables.copy(codeSplit[1], codeSplit[2]);
        return "";
    }
}

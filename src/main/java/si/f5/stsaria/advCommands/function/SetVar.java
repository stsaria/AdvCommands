package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class SetVar implements Function{
    @Override
    public String syntax() {
        return "setvarG? [a-zA-Z0-9.]+ .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        GlobalVariables.set(codeSplit[1], code.replaceFirst("setvarG? "+codeSplit[1]+" ", ""));
        return "";
    }
}

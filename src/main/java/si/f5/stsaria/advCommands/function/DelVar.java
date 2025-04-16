package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class DelVar implements Function{
    @Override
    public String syntax() {
        return "delvarG? [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        GlobalVariables.delete(codeSplit[1]);
        return "";
    }
}

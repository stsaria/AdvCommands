package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class ToInt implements Function{
    @Override
    public String syntax() {
        return "toint [a-zA-Z0-9.]+ [0-9]+(\\.[0-9]+)?";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        try{
            GlobalVariables.set(codeSplit[1], String.valueOf((long) Double.parseDouble(codeSplit[2])));
        } catch (NumberFormatException ignore) {
            return "error: cant cast source to double";
        }
        return "";
    }
}

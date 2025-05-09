package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class RegexF implements Function{
    @Override
    public String syntax() {
        return "regex [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ (?s).*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.containsDirect(codeSplit[2])) return "error: search source variable not found";
        boolean isMatch = GlobalVariables.get(codeSplit[2]).matches(code.replaceFirst("regex "+codeSplit[1]+" "+codeSplit[2]+" ", ""));
        GlobalVariables.set(codeSplit[1], isMatch ? "true" : "false");
        return "";
    }
}

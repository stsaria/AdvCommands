package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class StrToList implements Function{
    @Override
    public String syntax() {
        return "strtolist [a-zA-Z0-9.]+ .+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        int i = 0;
        for (String c : code.replaceFirst("strtolist "+codeSplit[1]+" ", "").split("")){
            GlobalVariables.set(code+"."+i, c);
            i++;
        }
        return "";
    }
}

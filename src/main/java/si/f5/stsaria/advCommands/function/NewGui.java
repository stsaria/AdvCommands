package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.GuisManager;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class NewGui implements Function{
    @Override
    public String syntax() {
        return "newgui [a-zA-Z0-9]+ [a-zA-Z0-9.]+ .+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.contains(codeSplit[2])) return "error: itemStacks var not found";
        else if (!(9 <= GlobalVariables.length(codeSplit[2]) && GlobalVariables.length(codeSplit[2]) <= 54 && GlobalVariables.length(codeSplit[2]) % 9 == 0)) return "error: size of the itemstacks must be a multiple of 9 between 9 and 54 slots";
        int r = GuisManager.add(codeSplit[1], code.replaceFirst("newgui "+codeSplit[1]+" "+codeSplit[2]+" ", ""), codeSplit[2]);
        if (r == 1) return "error: gui already exists";
        return "";
    }
}

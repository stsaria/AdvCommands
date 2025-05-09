package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class Replace implements Function{
    @Override
    public String syntax() {
        return "replace [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        for (int i = 2; i < 5; i++){
            if (!GlobalVariables.containsDirect(codeSplit[i])) return "error: args."+i+" variable not found";
        }
        GlobalVariables.set(codeSplit[1], GlobalVariables.get(codeSplit[2]).replaceAll(GlobalVariables.get(codeSplit[3]), GlobalVariables.get(codeSplit[4])));
        return "";
    }
}

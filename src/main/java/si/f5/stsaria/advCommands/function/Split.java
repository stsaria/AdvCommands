package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class Split implements Function{
    @Override
    public String syntax() {
        return "split [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ (?s).+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.containsDirect(codeSplit[2])) return "error: split source variable not found";
        int i = 0;
        for (String c : GlobalVariables.get(codeSplit[2]).split(code.replaceFirst("strtolist "+codeSplit[1]+" "+codeSplit[2]+" ", ""))){
            GlobalVariables.set(code+"."+i, c);
            i++;
        }
        return "";
    }
}

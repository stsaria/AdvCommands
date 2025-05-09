package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class StructToJson implements Function{
    @Override
    public String syntax() {
        return "structtojson [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.contains(codeSplit[2])) return "error: struct variable not found";
        GlobalVariables.set(codeSplit[1], GlobalVariables.toJson(codeSplit[2]));
        return "";
    }
}

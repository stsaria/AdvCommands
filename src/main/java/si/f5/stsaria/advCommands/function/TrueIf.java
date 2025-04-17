package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.FunctionsManager;

public class TrueIf implements Function{
    @Override
    public String syntax() {
        return "trueif (true|false) .+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].equals("true")){
            Function func = FunctionsManager.get(codeSplit[2]);
            if (func == null) return "error: func not found";
            return func.execute(code.replaceFirst("trueif true ", ""));
        }
        return "";
    }
}

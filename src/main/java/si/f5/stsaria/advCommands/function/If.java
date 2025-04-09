package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.FunctionsManager;

public class If implements Function {
    @Override
    public String syntax() {
        return "if (true|false) [a-zA-Z0-9]+ else [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].equals("true")){
            Function func = FunctionsManager.getFunction(codeSplit[2]);
            if (func == null) return "error: func not found";
            return func.execute(codeSplit[1]);
        } else if (codeSplit[1].equals("false")){
            Function func = FunctionsManager.getFunction(codeSplit[4]);
            if (func == null) return "error: func not found";
            return func.execute(codeSplit[4]);
        }
        return "";
    }
}

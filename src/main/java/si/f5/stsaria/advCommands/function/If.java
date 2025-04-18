package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;

public class If implements Function {
    @Override
    public String syntax() {
        return "if (true|false) [a-zA-Z0-9]+ else [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].equals("true")){
            Function func = Functions.get(codeSplit[2]);
            if (func == null) return "error: func not found";
            return func.execute(codeSplit[1]);
        } else if (codeSplit[1].equals("false")){
            Function func = Functions.get(codeSplit[4]);
            if (func == null) return "error: func not found";
            return func.execute(codeSplit[4]);
        }
        return "";
    }
}

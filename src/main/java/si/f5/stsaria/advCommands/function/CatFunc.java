package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.FunctionsManager;

public class CatFunc implements Function{
    @Override
    public String syntax() {
        return "catfunc [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Function func = FunctionsManager.get(codeSplit[1]);
        if (!(func instanceof UserFunction)) return "error: func not found";
        return "\n"+((UserFunction) func).cat()+"\n";
    }
}

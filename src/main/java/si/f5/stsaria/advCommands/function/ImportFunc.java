package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;

import java.util.Base64;

public class ImportFunc implements Function{
    @Override
    public String syntax() {
        return "importfunc [a-zA-Z0-9+/.=]*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].split("\\.").length != 2) return "error: broken code";
        if (Functions.addDirect(codeSplit[1].split("\\.")[0], new String(Base64.getDecoder().decode(codeSplit[1].split("\\.")[1]))) == 1) return "error: func exists";
        return "";
    }
}

package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ExportFunc implements Function{
    @Override
    public String syntax() {
        return "exportfunc [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Function func = Functions.get(codeSplit[2]);
        if (!(func instanceof UserFunction)) return "error: func not found";
        GlobalVariables.set(codeSplit[1], codeSplit[2]+"."+Base64.getEncoder().encodeToString(((UserFunction) func).lines().getBytes(StandardCharsets.UTF_8)));
        return "";
    }
}

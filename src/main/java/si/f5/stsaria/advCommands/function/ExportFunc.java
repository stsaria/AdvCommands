package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ExportFunc extends Function {
    @Override
    public String syntax() {
        return "exportfunc [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Function func = Functions.get(codeSplit[1]);
        if (!(func instanceof UserFunction)) return new ErrorV("func not found");
        return new OneResultV(codeSplit[1]+"."+Base64.getEncoder().encodeToString(((UserFunction) func).lines().getBytes(StandardCharsets.UTF_8)));
    }
}

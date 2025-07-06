package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.Variables;

public class CopyVar implements Function{
    @Override
    public String syntax() {
        return "copyvar [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        variables.copy(codeSplit[1], codeSplit[2]);
        return null;
    }
}

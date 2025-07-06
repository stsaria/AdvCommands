package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

public class Size implements Function{
    @Override
    public String syntax() {
        return "size [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        return new OneResultV(String.valueOf(variables.size(codeSplit[2])));
    }
}

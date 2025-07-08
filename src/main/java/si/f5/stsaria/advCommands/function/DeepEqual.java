package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

public class DeepEqual extends Function {
    @Override
    public String syntax() {
        return "deepequal [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        boolean isEqual = variables.deepEqual(codeSplit[1], codeSplit[2]);
        return new OneResultV(isEqual ? "true" : "false");
    }
}

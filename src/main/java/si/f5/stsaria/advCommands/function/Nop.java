package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.Variables;

public class Nop extends Function {
    @Override
    public String syntax() {
        return ".*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        return null;
    }
}

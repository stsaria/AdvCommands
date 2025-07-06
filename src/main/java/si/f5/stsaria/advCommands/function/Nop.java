package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.Variables;

public class Nop implements Function{
    @Override
    public String syntax() {
        return "nop";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        return null;
    }
}

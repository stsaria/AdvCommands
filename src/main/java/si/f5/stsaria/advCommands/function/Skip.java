package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

public class Skip extends Function {
    @Override
    public String syntax() {
        return "skip";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        return new OneResultV("plsskip");
    }
}

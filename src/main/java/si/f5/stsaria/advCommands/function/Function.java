package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.Variables;

public interface Function {
    String syntax();
    Variables execute(String code, Variables variables);
}

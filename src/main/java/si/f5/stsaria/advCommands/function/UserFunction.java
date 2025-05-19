package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.Variables;

public interface UserFunction extends Function{
    String getName();
    void setVariable(String name, String variable);
    void concat(String rootName, Variables variables);
    String lines();
    String formatedLines();
    int size();
}

package si.f5.stsaria.advCommands.variables;

public class VariablesUtil {
    public static void concat(Variables sourceVariables, String rootName, Variables variables){
        variables.getVariableMap().forEach((n, v) -> sourceVariables.set(rootName+(!rootName.isEmpty() ? "." : "")+n, v));
    }
}

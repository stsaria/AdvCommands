package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

public class LocalToGlobal extends Function {
    @Override
    public String syntax() {
        return "localtoglobal [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        variables.getVariableMap().forEach((n, v) -> {
            if (n.equals(codeSplit[1])) GlobalVariables.set(codeSplit[2], v);
            else if (n.startsWith(codeSplit[1]+".")) GlobalVariables.set(codeSplit[2]+"."+n.replaceFirst(codeSplit[1]+".", ""), v);
        });
        return null;
    }
}

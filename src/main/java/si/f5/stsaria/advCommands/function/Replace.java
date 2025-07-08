package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

public class Replace extends Function {
    @Override
    public String syntax() {
        return "replace [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        for (int i = 2; i < 5; i++){
            if (!variables.containsDirect(codeSplit[i])) return new ErrorV("args."+i+" variable not found");
        }
        return new OneResultV(variables.get(codeSplit[1]).replaceAll(variables.get(codeSplit[2]), variables.get(codeSplit[3])));
    }
}

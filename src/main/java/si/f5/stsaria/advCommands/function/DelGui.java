package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Guis;
import si.f5.stsaria.advCommands.variables.Variables;

public class DelGui extends Function {
    @Override
    public String syntax() {
        return "delgui [a-zA-Z0-9]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Guis.remove(codeSplit[1]);
        return null;
    }
}

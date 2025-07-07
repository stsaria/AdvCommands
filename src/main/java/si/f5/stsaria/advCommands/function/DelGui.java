package si.f5.stsaria.advCommands.function;

import com.google.errorprone.annotations.Var;
import si.f5.stsaria.advCommands.manager.Guis;
import si.f5.stsaria.advCommands.variables.Variables;

public class DelGui implements Function{
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

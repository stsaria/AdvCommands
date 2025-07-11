package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.Guis;
import si.f5.stsaria.cakeLang.variables.Variables;

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

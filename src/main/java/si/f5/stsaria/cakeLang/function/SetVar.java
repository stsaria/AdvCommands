package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.Variables;

public class SetVar extends Function {
    @Override
    public String syntax() {
        return "setvar [a-zA-Z0-9.]+ (?s).*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        variables.set(codeSplit[1], code.replaceFirst("setvar "+codeSplit[1]+" ", ""));
        return null;
    }
}

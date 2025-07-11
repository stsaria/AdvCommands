package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.Variables;

public class Nop extends Function {
    @Override
    public String syntax() {
        return ".*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        return null;
    }
}

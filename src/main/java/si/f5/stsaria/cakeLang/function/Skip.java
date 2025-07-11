package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.OneResultV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class Skip extends Function {
    @Override
    public String syntax() {
        return "skip";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        return new OneResultV("plsskip");
    }
}

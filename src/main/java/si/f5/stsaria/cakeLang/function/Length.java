package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.OneResultV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class Length extends Function {
    @Override
    public String syntax() {
        return "length [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        return new OneResultV(String.valueOf(variables.length(codeSplit[1])));
    }
}

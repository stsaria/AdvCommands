package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.Variables;

public class DelVar extends Function {
    @Override
    public String syntax() {
        return "delvar [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        variables.delete(codeSplit[1]);
        return null;
    }
}

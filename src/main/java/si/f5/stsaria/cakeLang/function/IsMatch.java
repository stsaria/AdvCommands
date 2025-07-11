package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.OneResultV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class IsMatch extends Function {
    @Override
    public String syntax() {
        return "ismatch [a-zA-Z0-9.]+ (?s).*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.containsDirect(codeSplit[1])) return new ErrorV("search source variable not found");
        boolean isMatch = variables.get(codeSplit[1]).matches(code.replaceFirst("regex "+codeSplit[1]+" "+codeSplit[2]+" ", ""));
        return new OneResultV(isMatch ? "true" : "false");
    }
}

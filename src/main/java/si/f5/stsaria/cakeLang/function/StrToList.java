package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

public class StrToList extends Function {
    @Override
    public String syntax() {
        return "strtolist (?s).*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        int i = 0;
        Variables result = new EmpVariables();
        for (String c : code.replaceFirst("strtolist ", "").split("")){
            result.set("0."+i, c);
            i++;
        }
        result.set("resulttype", "oneresult");
        return result;
    }
}

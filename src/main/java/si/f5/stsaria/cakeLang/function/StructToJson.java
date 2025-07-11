package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.OneResultV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class StructToJson extends Function {
    @Override
    public String syntax() {
        return "structtojson [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.contains(codeSplit[2])) return new ErrorV("struct variable not found");
        return new OneResultV(variables.toJson(codeSplit[2]));
    }
}

package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.Base64;

public class ImportFunc extends Function {
    @Override
    public String syntax() {
        return "importfunc [a-zA-Z0-9+/.=]*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (codeSplit[1].split("\\.").length != 2) return new ErrorV("broken code");
        if (Functions.addDirect(codeSplit[1].split("\\.")[0], new String(Base64.getDecoder().decode(codeSplit[1].split("\\.")[1]))) == 1) return new ErrorV("func exists");
        return null;
    }
}

package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.Guis;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class NewGui extends Function {
    @Override
    public String syntax() {
        return "newgui [a-zA-Z0-9]+ [a-zA-Z0-9.]+ .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.contains(codeSplit[2])) return new ErrorV("itemstacks var not found");
        else if (!(9 <= variables.length(codeSplit[2]) && variables.length(codeSplit[2]) <= 54 && variables.length(codeSplit[2]) % 9 == 0)) return new ErrorV("size of the itemstacks must be a multiple of 9 between 9 and 54 slots");
        int r = Guis.add(codeSplit[1], code.replaceFirst("newgui "+codeSplit[1]+" "+codeSplit[2]+" ", ""), codeSplit[2], variables);
        if (r == 1) return new ErrorV("gui already exists");
        
        return null;
    }
}

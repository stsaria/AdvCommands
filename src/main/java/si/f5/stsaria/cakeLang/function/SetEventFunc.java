package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.manager.EventFunctions;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class SetEventFunc extends Function {
    @Override
    public String syntax() {
        return "seteventfunc [a-zA-Z0-9]+ [a-zA-Z0-9]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        int r = EventFunctions.set(codeSplit[1], codeSplit[2]);
        if (r == 1){
            return new ErrorV("not found event type");
        } else if (r == 2){
            return new ErrorV("func not found");
        } else if (r == 3){
            return new ErrorV("only user defined functions can be registered");
        }
        return null;
    }
}

package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.EventFunctions;

public class SetEventFunc implements Function{

    @Override
    public String syntax() {
        return "seteventfunc [a-zA-Z0-9]+ [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        int r = EventFunctions.set(code.split(" ")[1], code.split(" ")[2]);
        if (r == 1){
            return "error: not found event type";
        } else if (r == 2){
            return "error: func not found";
        } else if (r == 3){
            return "error: only user defined functions can be registered";
        }
        return "";
    }
}

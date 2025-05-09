package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.concurrent.atomic.AtomicInteger;

public class VariablesF  implements Function{
    @Override
    public String syntax() {
        return "variables [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        AtomicInteger i = new AtomicInteger(0);
        GlobalVariables.getAll().forEach((n, v) -> GlobalVariables.set(code.split(" ")[1]+"."+i.getAndIncrement(), n+" -> "+v));
        return "";
    }
}

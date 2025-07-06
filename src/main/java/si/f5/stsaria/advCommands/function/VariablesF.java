package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.concurrent.atomic.AtomicInteger;

public class VariablesF implements Function{
    @Override
    public String syntax() {
        return "variables [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        AtomicInteger i = new AtomicInteger(0);
        Variables result = new EmpVariables();
        GlobalVariables.getAll().forEach((n, v) -> result.set("0."+i.getAndIncrement(), n+" -> "+v));
        result.set("resulttype", "oneresult");
        return result;
    }
}

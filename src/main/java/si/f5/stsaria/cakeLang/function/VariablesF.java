package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.GlobalVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.concurrent.atomic.AtomicInteger;

public class VariablesF extends Function {
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

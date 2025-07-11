package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

public abstract class Function implements Cloneable{
    protected Variables variables = new EmpVariables();
    public abstract String syntax();
    public abstract Variables execute(String code, Variables variables);
    public Function clone(){
        try {
            Function function = (Function) super.clone();
            function.variables = new EmpVariables();
            return function;
        } catch (CloneNotSupportedException e){
            return new Nop();
        }
    }
}

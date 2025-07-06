package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.variables.Variables;

public class InfoRunFunc {
    private final Function function;
    private final String code;
    private final Variables variables;
    public InfoRunFunc(Function function, String code, Variables variables){
        this.function = function;
        this.code = code;
        this.variables = variables;
    }
    public Function getFunction() {
        return this.function;
    }
    public String getCode() {
        return this.code;
    }
    public Variables getVariables(){
        return this.variables;
    }
}

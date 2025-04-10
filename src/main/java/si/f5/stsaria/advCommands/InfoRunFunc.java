package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.function.Function;

public class InfoRunFunc {
    private final Function function;
    private final String code;
    public InfoRunFunc(Function function, String code){
        this.function = function;
        this.code = code;
    }
    public Function getFunction() {
        return this.function;
    }
    public String getCode() {
        return this.code;
    }
}

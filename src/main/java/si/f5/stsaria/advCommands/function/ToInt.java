package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

public class ToInt extends Function {
    @Override
    public String syntax() {
        return "toint [0-9]+(\\.[0-9]+)?";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        String strInt;
        try{
            strInt = String.valueOf((long) Double.parseDouble(codeSplit[1]));
        } catch (NumberFormatException ignore) {
            return new ErrorV("cant cast source to double");
        }
        return new OneResultV(strInt);
    }
}

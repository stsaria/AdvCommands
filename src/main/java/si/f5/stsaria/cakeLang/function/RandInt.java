package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.OneResultV;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.Random;

public class RandInt extends Function {
    @Override
    public String syntax() {
        return "randint [+-]?\\d+ [+-]?\\d+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        int firstRange = Integer.parseInt(codeSplit[1]);
        int secondRange = Integer.parseInt(codeSplit[2]);
        if (firstRange > secondRange) return new ErrorV("the beginning of the range is greater than the end");
        return new OneResultV(String.valueOf(new Random().nextInt(secondRange-firstRange)+firstRange));
    }
}

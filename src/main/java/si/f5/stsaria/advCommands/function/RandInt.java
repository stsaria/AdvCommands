package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.Random;

public class RandInt implements Function{
    @Override
    public String syntax() {
        return "randint [a-zA-Z0-9.]+ [+-]?\\d+ [+-]?\\d+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        int firstRange = Integer.parseInt(codeSplit[2]);
        int secondRange = Integer.parseInt(codeSplit[3]);
        if (firstRange > secondRange) return "error: the beginning of the range is greater than the end";
        GlobalVariables.set(codeSplit[1], String.valueOf(new Random().nextInt(secondRange-firstRange)+firstRange));
        return "";
    }
}

package si.f5.stsaria.advCommands.function;

import java.util.Random;

public class RandInt implements Function{
    @Override
    public String syntax() {
        return "randint [+-]?\\d+ [+-]?\\d+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        String[] codeSplit = code.split(" ");
        int firstRange = Integer.parseInt(codeSplit[1]);
        int secondRange = Integer.parseInt(codeSplit[2]);
        if (firstRange > secondRange) return "error: the beginning of the range is greater than the end";
        new SetVar().execute("setvarG "+codeSplit[3]+" "+(new Random().nextInt(secondRange-firstRange)+firstRange));
        return "";
    }
}

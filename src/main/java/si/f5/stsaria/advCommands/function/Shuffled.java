package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffled implements Function{
    @Override
    public String syntax() {
        return "shuffle [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        int len = variables.length(codeSplit[1]);
        int i;
        List<Integer> indexes = new ArrayList<>();
        for (i = 0; i < len; i++){
            indexes.set(i, i);
        }
        Collections.shuffle(indexes);
        Variables shuffled = new EmpVariables();
        for (i = 0; i < len; i++){
            shuffled.set(String.valueOf(indexes.get(i)), variables.get(codeSplit[1]+"."+i));
        }
        variables.concat(codeSplit[1], shuffled);
        return null;
    }
}

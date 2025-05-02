package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffled implements Function{
    @Override
    public String syntax() {
        return "shuffled [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        int len = GlobalVariables.length(codeSplit[1]);
        int i;
        List<Integer> indexes = new ArrayList<>();
        for (i = 0; i < len; i++){
            indexes.set(i, i);
        }
        Collections.shuffle(indexes);
        for (i = 0; i < len; i++){
            GlobalVariables.copy(codeSplit[1]+"."+i, codeSplit[2]+"."+indexes.get(i));
        }
        return "";
    }
}

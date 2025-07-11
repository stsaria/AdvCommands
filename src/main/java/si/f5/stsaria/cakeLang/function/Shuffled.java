package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffled extends Function {
    @Override
    public String syntax() {
        return "shuffled [a-zA-Z0-9.]+";
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
            shuffled.set("0."+indexes.get(i), variables.get(codeSplit[1]+"."+i));
        }
        shuffled.set("resulttype", "oneresult");
        return shuffled;
    }
}

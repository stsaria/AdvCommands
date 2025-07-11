package si.f5.stsaria.cakeLang.function;


import si.f5.stsaria.cakeLang.variables.GlobalVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

public class GlobalToLocal extends Function {
    @Override
    public String syntax() {
        return "globaltolocal [a-zA-Z0-9.]+ [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        GlobalVariables.getAll().forEach((n, v) -> {
            if (n.equals(codeSplit[1])) variables.set(codeSplit[2], v);
            else if (n.startsWith(codeSplit[1]+".")) variables.set(codeSplit[2]+"."+n.replaceFirst(codeSplit[1]+".", ""), v);
        });
        return null;
    }
}

package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.SuspiciousUtils;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.concurrent.atomic.AtomicReference;

public class GetName implements Function{
    @Override
    public String syntax() {
        return "getname [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        AtomicReference<String> name = new AtomicReference<>("null");
        try {
            GlobalVariables.getAll().forEach((n, v) -> {
                if (!n.startsWith(codeSplit[1] + ".")) return;
                if (v.equals(code.replaceFirst("getkey " + codeSplit[1] + " " + codeSplit[2] + " ", ""))) {
                    name.set(n);
                    SuspiciousUtils.fail();
                }
            });
        } catch (Exception ignore) {}
        GlobalVariables.set(codeSplit[1], name.toString());
        return "";
    }
}

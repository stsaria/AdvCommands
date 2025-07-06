package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.SuspiciousUtils;
import si.f5.stsaria.advCommands.variables.OneResultV;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.concurrent.atomic.AtomicReference;

public class GetName implements Function{
    @Override
    public String syntax() {
        return "getname .*";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        AtomicReference<String> name = new AtomicReference<>("null");
        try {
            variables.getVariableMap().forEach((n, v) -> {
                if (!n.startsWith(codeSplit[1] + ".")) return;
                if (v.equals(code.replaceFirst("getname ", ""))) {
                    name.set(n);
                    SuspiciousUtils.fail();
                }
            });
        } catch (Exception ignore) {}
        return new OneResultV(name.get());
    }
}

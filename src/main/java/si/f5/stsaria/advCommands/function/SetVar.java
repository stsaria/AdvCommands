package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import si.f5.stsaria.advCommands.FunctionsManager;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

public class SetVar implements Function{
    @Override
    public String syntax() {
        return "setVar [a-zA-Z0-9]+ .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        String[] codeSplit = code.split(" ");
        GlobalVariables.setVariable(codeSplit[1], code.replaceFirst("setVar "+codeSplit[1]+" ", ""));
        return "";
    }
}

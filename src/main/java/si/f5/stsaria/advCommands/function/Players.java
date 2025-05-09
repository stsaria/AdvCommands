package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.PlayerV;

public class Players implements Function{
    @Override
    public String syntax() {
        return "players [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        Bukkit.getOnlinePlayers().forEach(p -> new PlayerV(p).getVariableMap().forEach((n, v) -> GlobalVariables.set(code.split(" ")[1]+"."+n, v)));
        return "";
    }
}

package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.PlayerV;
import si.f5.stsaria.advCommands.variables.Variables;

public class Players extends Function {
    @Override
    public String syntax() {
        return "players";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        Variables result = new EmpVariables();
        Bukkit.getOnlinePlayers().forEach(p -> result.concat("0."+p.getName(), new PlayerV(p)));
        result.set("resulttype", "oneresult");
        return result;
    }
}

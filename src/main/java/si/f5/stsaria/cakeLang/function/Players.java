package si.f5.stsaria.cakeLang.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.PlayerV;
import si.f5.stsaria.cakeLang.variables.Variables;

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

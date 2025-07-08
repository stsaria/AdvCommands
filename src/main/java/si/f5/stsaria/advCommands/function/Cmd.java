package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.advCommands.variables.Variables;

public class Cmd extends Function {
    @Override
    public String syntax() {
        return "cmd (?s).+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), code.replaceFirst("cmd ", "").replace("&lt", "<").replace("&gt", ">"));
        return null;
    }
}

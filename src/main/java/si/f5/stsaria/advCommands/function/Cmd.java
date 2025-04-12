package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;

public class Cmd implements Function{
    @Override
    public String syntax() {
        return "cmd .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), code.replaceFirst("cmd ", "").replace("&lt", "<").replace("&gt", ">"));
        return "";
    }
}

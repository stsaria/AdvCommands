package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CloseGui implements Function{
    @Override
    public String syntax() {
        return "closegui [a-zA-Z0-9_.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Player p = Bukkit.getPlayer(codeSplit[1]);
        if (p == null) return "error: player not found";
        p.closeInventory();
        return "";
    }
}

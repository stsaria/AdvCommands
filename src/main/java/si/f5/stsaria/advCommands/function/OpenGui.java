package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.GuisManager;

public class OpenGui implements Function{
    @Override
    public String syntax() {
        return "opengui [a-zA-Z0-9]+ [a-zA-Z0-9_.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GuisManager.contains(codeSplit[1])) return "error: gui not found";
        Player player = Bukkit.getPlayer(codeSplit[2]);
        if (player == null) return "error: player not found";
        GuisManager.get(codeSplit[1]).open(player);
        return "";
    }
}

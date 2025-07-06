package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.manager.Guis;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.Variables;

public class OpenGui implements Function{
    @Override
    public String syntax() {
        return "opengui [a-zA-Z0-9]+ [a-zA-Z0-9_.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!Guis.contains(codeSplit[1])) return new ErrorV("gui not found");
        Player player = Bukkit.getPlayer(codeSplit[2]);
        if (player == null) return new ErrorV("player not found");
        Guis.get(codeSplit[1]).open(player);
        return null;
    }
}

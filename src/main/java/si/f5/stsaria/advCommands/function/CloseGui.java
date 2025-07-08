package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.Variables;

public class CloseGui extends Function {
    @Override
    public String syntax() {
        return "closegui [a-zA-Z0-9_.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Player p = Bukkit.getPlayer(codeSplit[1]);
        if (p == null) return new ErrorV("player not found");
        p.closeInventory();
        return null;
    }
}

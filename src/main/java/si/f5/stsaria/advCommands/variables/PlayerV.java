package si.f5.stsaria.advCommands.variables;

import org.bukkit.entity.Player;

public class PlayerV extends Variables {
    public PlayerV(Player player){
        this.set("name", player.getName());
        this.set("displayname", player.getDisplayName().replace("<", "&lt").replace(">", "&gt").replace("\n", "\\n"));
        new LocationV(player.getLocation()).getVariableMap().forEach((n, v) -> this.set("location."+n, v));
    }
}

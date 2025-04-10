package si.f5.stsaria.advCommands.variables;

import org.bukkit.entity.Player;

public class PlayerV extends Variables {
    public PlayerV(Player player){
        this.setVariable("name", player.getName());
        this.setVariable("displayname", player.getDisplayName().replaceAll("[<>]", ""));
        new LocationV(player.getLocation()).getVariableMap().forEach((n, v) -> this.setVariable("location."+n, v));
    }
}

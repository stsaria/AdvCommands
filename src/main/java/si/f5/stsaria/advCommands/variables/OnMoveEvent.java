package si.f5.stsaria.advCommands.variables;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class OnMoveEvent extends Variables{
    public OnMoveEvent(Player player, Location from, Location to){
        this.setVariable("player", player.getName());
        new PlayerV(player).getVariableMap().forEach((n, v) -> this.setVariable("player."+n, v));
        new LocationV(from).getVariableMap().forEach((n, v) -> this.setVariable("from."+n, v));
        new LocationV(to).getVariableMap().forEach((n, v) -> this.setVariable("to."+n, v));
    }
}

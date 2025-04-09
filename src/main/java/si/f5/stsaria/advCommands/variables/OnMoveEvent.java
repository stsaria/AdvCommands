package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class OnMoveEvent extends Variables{
    public OnMoveEvent(PlayerMoveEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.setVariable("player."+n, v));
        new LocationV(e.getFrom()).getVariableMap().forEach((n, v) -> this.setVariable("from."+n, v));
        new LocationV(Objects.requireNonNull(e.getTo())).getVariableMap().forEach((n, v) -> this.setVariable("to."+n, v));
    }
}

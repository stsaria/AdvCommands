package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class OnMoveEvent extends Variables{
    public OnMoveEvent(PlayerMoveEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
        this.concat("from", new LocationV(e.getFrom()));
        this.concat("to", new LocationV(Objects.requireNonNull(e.getTo())));
    }
}

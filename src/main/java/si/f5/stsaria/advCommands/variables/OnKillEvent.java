package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnKillEvent extends Variables{
    public OnKillEvent(PlayerDeathEvent e){
        new PlayerV(e.getEntity()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new PlayerV(Objects.requireNonNull(e.getEntity().getKiller()))
        .getVariableMap().forEach((n, v) -> this.set("killer."+n, v));
    }
}

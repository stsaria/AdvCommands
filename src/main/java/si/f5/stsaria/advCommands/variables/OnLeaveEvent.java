package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveEvent extends Variables{
    public OnLeaveEvent(PlayerQuitEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
    }
}

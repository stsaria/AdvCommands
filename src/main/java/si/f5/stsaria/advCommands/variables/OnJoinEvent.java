package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinEvent extends Variables{
    public OnJoinEvent(PlayerJoinEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.setVariable("player."+n, v));
    }
}

package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveEvent extends Variables{
    public OnLeaveEvent(PlayerQuitEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
    }
}

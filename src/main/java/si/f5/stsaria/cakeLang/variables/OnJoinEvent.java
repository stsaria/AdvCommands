package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinEvent extends Variables{
    public OnJoinEvent(PlayerJoinEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
    }
}

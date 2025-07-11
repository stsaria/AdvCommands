package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatEvent extends Variables{
    public OnChatEvent(AsyncPlayerChatEvent e){
        this.set("message", e.getMessage().replace("<", "&lt").replace(">", "&gt"));
        this.concat("player", new PlayerV(e.getPlayer()));
    }
}

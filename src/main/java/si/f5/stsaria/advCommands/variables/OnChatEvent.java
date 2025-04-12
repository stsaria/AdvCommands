package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChatEvent extends Variables{
    public OnChatEvent(AsyncPlayerChatEvent e){
        this.setVariable("message", e.getMessage().replace("<", "&lt").replace(">", "&gt").replace("\n", "\\n"));
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.setVariable("player."+n, v));
    }
}

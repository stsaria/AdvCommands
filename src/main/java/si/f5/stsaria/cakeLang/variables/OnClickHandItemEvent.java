package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.player.PlayerInteractEvent;

public class OnClickHandItemEvent extends Variables{
    public OnClickHandItemEvent(PlayerInteractEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
        this.concat("itemstack", new ItemStackV(e.getItem()));
    }
}

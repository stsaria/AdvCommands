package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.player.PlayerDropItemEvent;


public class OnDropEvent extends Variables{
    public OnDropEvent(PlayerDropItemEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
        this.concat("itemstack", new ItemStackV(e.getItemDrop().getItemStack()));
    }
}

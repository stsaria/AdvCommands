package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class OnClickHandItemEvent extends Variables{
    public OnClickHandItemEvent(PlayerInteractEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new ItemStackV(Objects.requireNonNull(e.getItem())).getVariableMap().forEach((n, v) -> this.set("itemstack."+n, v));
    }
}

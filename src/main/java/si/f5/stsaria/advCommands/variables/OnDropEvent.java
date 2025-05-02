package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class OnDropEvent extends Variables{
    public OnDropEvent(PlayerDropItemEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new ItemStackV(Objects.requireNonNull(e.getItemDrop().getItemStack())).getVariableMap().forEach((n, v) -> this.set("itemstack."+n, v));
    }
}

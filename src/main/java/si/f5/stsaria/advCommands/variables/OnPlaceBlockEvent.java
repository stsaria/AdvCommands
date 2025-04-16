package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.block.BlockPlaceEvent;

public class OnPlaceBlockEvent extends Variables{
    public OnPlaceBlockEvent(BlockPlaceEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new BlockV(e.getBlock()).getVariableMap().forEach((n, v) -> this.set("block."+n, v));
        new BlockV(e.getBlockPlaced()).getVariableMap().forEach((n, v) -> this.set("placedblock."+n, v));
    }
}

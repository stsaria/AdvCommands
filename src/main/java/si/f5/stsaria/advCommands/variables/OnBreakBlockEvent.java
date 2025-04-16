package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakBlockEvent extends Variables{
    public OnBreakBlockEvent(BlockBreakEvent e){
        new PlayerV(e.getPlayer()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new BlockV(e.getBlock()).getVariableMap().forEach((n, v) -> this.set("block."+n, v));
    }
}

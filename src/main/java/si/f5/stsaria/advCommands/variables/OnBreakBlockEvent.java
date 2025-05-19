package si.f5.stsaria.advCommands.variables;

import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakBlockEvent extends Variables{
    public OnBreakBlockEvent(BlockBreakEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
        this.concat("block", new BlockV(e.getBlock()));
    }
}

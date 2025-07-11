package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.block.BlockPlaceEvent;

public class OnPlaceBlockEvent extends Variables{
    public OnPlaceBlockEvent(BlockPlaceEvent e){
        this.concat("player", new PlayerV(e.getPlayer()));
        this.concat("block", new BlockV(e.getBlock()));
        this.concat("placedblock", new BlockV(e.getBlockPlaced()));
    }
}

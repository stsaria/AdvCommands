package si.f5.stsaria.cakeLang.variables;

import org.bukkit.block.Block;

public class BlockV extends Variables{
    public BlockV(Block block){
        this.set("materialname", block.getType().name().toLowerCase());
        this.concat("location", new LocationV(block.getLocation()));
    }
}

package si.f5.stsaria.advCommands.variables;

import org.bukkit.block.Block;

public class BlockV extends Variables{
    public BlockV(Block block){
        this.set("materialname", block.getType().name().toLowerCase());
        new LocationV(block.getLocation()).getVariableMap().forEach((n, v) -> this.set("location."+n, v));
    }
}

package si.f5.stsaria.advCommands.variables;

import org.bukkit.block.Block;

public class BlockV extends Variables{
    public BlockV(Block block){
        this.setVariable("materialName", block.getType().name().toLowerCase());
        new LocationV(block.getLocation()).getVariableMap().forEach((n, v) -> this.setVariable("location."+n, v));
    }
}

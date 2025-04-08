package si.f5.stsaria.advCommands.variables;

import org.bukkit.Location;

public class LocationV extends Variables{
    public LocationV(Location location){
        this.setVariable("x", String.valueOf(location.getBlockX()));
        this.setVariable("y", String.valueOf(location.getBlockY()));
        this.setVariable("z", String.valueOf(location.getBlockZ()));
    }
}

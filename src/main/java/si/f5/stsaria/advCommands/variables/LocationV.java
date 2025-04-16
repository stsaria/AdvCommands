package si.f5.stsaria.advCommands.variables;

import org.bukkit.Location;

import java.util.Objects;

public class LocationV extends Variables{
    public LocationV(Location location){
        this.set("world", Objects.requireNonNull(location.getWorld()).getName());
        this.set("x", String.valueOf(location.getBlockX()));
        this.set("y", String.valueOf(location.getBlockY()));
        this.set("z", String.valueOf(location.getBlockZ()));
    }
}

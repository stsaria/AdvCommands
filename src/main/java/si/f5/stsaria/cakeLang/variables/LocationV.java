package si.f5.stsaria.cakeLang.variables;

import org.bukkit.Location;

import java.util.Objects;

public class LocationV extends Variables{
    public LocationV(Location location){
        this.set("world", Objects.requireNonNull(location.getWorld()).getName());
        this.set("x", String.valueOf(location.getX()));
        this.set("y", String.valueOf(location.getY()));
        this.set("z", String.valueOf(location.getZ()));
        this.set("blockx", String.valueOf(location.getBlockX()));
        this.set("blocky", String.valueOf(location.getBlockY()));
        this.set("blockz", String.valueOf(location.getBlockZ()));
        this.set("yaw", String.valueOf(location.getYaw()));
        this.set("pitch", String.valueOf(location.getPitch()));
    }
}

package si.f5.stsaria.cakeLang.variables;

import org.bukkit.entity.Entity;

public class EntityV extends Variables {
    public EntityV(Entity entity){
        this.set("name", entity.getName());
        this.set("entityid", String.valueOf(entity.getEntityId()));
        this.set("uuid", String.valueOf(entity.getUniqueId()));
        this.set("isdead", entity.isDead() ? "true" : "false");
        this.concat("location", new LocationV(entity.getLocation()));
    }
}

package si.f5.stsaria.cakeLang.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageEvent extends Variables{
    public OnDamageEvent(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            this.set("type", "player");
            this.concat("player", new PlayerV((Player) e.getEntity()));
        } else {
            this.set("type", "entity");
            this.concat("entity", new EntityV(e.getEntity()));
        }
        this.set("damage", String.valueOf(e.getDamage()));
    }
}

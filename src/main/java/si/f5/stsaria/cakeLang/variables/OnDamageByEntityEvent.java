package si.f5.stsaria.cakeLang.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamageByEntityEvent extends Variables{
    public OnDamageByEntityEvent(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player){
            this.set("type", "player");
            this.concat("player", new PlayerV((Player) e.getEntity()));
        } else{
            this.set("type", "entity");
            this.concat("entity", new EntityV(e.getEntity()));
        }
        if (e.getDamager() instanceof Player){
            this.set("damagertype", "player");
            this.concat("damagerplayer", new PlayerV((Player) e.getDamager()));
        } else{
            this.set("damagertype", "entity");
            this.concat("entity", new EntityV(e.getDamager()));
        }
        this.set("damage", String.valueOf(e.getDamage()));
    }
}

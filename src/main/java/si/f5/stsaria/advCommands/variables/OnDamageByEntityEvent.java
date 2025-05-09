package si.f5.stsaria.advCommands.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamageByEntityEvent extends Variables{
    public OnDamageByEntityEvent(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player){
            this.set("type", "player");
            new PlayerV((Player) e.getEntity()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        } else{
            this.set("type", "entity");
            new EntityV(e.getEntity()).getVariableMap().forEach((n, v) -> this.set("entity."+n, v));
        }
        if (e.getDamager() instanceof Player){
            this.set("damagertype", "player");
            new PlayerV((Player) e.getDamager()).getVariableMap().forEach((n, v) -> this.set("damagerplayer."+n, v));
        } else{
            this.set("damagertype", "entity");
            new EntityV(e.getDamager()).getVariableMap().forEach((n, v) -> this.set("damagerentity."+n, v));
        }

        this.set("damage", String.valueOf(e.getDamage()));
    }
}

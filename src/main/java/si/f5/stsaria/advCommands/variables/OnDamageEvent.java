package si.f5.stsaria.advCommands.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageEvent extends Variables{
    public OnDamageEvent(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            this.set("type", "player");
            new PlayerV((Player) e.getEntity()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        } else {
            this.set("type", "entity");
            new EntityV(e.getEntity()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        }
        this.set("damage", String.valueOf(e.getDamage()));
    }
}

package si.f5.stsaria.cakeLang.variables;

import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnKillEvent extends Variables{
    public OnKillEvent(PlayerDeathEvent e){
        this.concat("player", new PlayerV(e.getEntity()));
        this.concat("killer", new PlayerV(Objects.requireNonNull(e.getEntity().getKiller())));
    }
}

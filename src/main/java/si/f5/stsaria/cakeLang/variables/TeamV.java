package si.f5.stsaria.cakeLang.variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.concurrent.atomic.AtomicInteger;

public class TeamV extends Variables{
    public TeamV(Team team){
        this.set("name", team.getName());
        this.set("displayname", team.getDisplayName());
        AtomicInteger i = new AtomicInteger(0);
        team.getEntries().forEach(
        pN -> {
                this.set("playernames." + i, pN);
                Player p = Bukkit.getPlayer(pN);
                if (p != null){
                    new PlayerV(p).getVariableMap().forEach((n, v) -> this.set("onlineplayers."+i+".", v));
                }
            }
        );

    }}

package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.TeamV;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Teams extends Function {
    @Override
    public String syntax() {
        return "teams";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        Variables result = new EmpVariables();
        AtomicInteger i = new AtomicInteger(0);
        Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeams().forEach(t -> result.concat("0."+i.getAndIncrement(), new TeamV(t)));
        result.set("resulttype", "oneresult");
        return result;
    }
}

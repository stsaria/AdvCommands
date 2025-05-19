package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.TeamV;

import java.util.Objects;

public class Teams implements Function{
    @Override
    public String syntax() {
        return "teams [a-zA-Z0-9.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeams().forEach(t -> GlobalVariables.concat(code.split(" ")[1], new TeamV(t)));
        return "";
    }
}

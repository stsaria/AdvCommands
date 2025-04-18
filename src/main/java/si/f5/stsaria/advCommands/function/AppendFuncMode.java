package si.f5.stsaria.advCommands.function;

import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.manager.AppendFuncModePlayers;
import si.f5.stsaria.advCommands.manager.Functions;

public class AppendFuncMode implements Function{
    private final Player player;
    public AppendFuncMode(Player player){
        this.player = player;
    }
    @Override
    public String syntax() {
        return "appendfuncmode [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Function func = Functions.get(codeSplit[1]);
        if (!(func instanceof UserFunction)) return "error: func not found";
        if (AppendFuncModePlayers.set(this.player, ((UserFunction) func).getName()) == 0){
            return "Start function append mode\n"+((UserFunction) func).cat();
        }
        return "error: Started function append mode";
    }
}

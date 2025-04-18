package si.f5.stsaria.advCommands.function;

import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.manager.AppendFuncModePlayers;

public class EndAppendFuncMode implements Function{
    private final Player player;
    public EndAppendFuncMode(Player player){
        this.player = player;
    }
    @Override
    public String syntax() {
        return "endappendfuncmode";
    }

    @Override
    public String execute(String code) {
        if (AppendFuncModePlayers.contains(this.player)){
            AppendFuncModePlayers.unset(this.player);
            return "End function append mode";
        }
        return "error: Not started function append mode";
    }
}

package si.f5.stsaria.advCommands.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AppendFuncModePlayers {
    private static final Map<Player, String> modePlayerMap = new HashMap<>();
    public static synchronized int set(Player player, String functionName){
        if (modePlayerMap.containsKey(player)) return 1;
        modePlayerMap.put(player, functionName);
        return 0;
    }
    public static synchronized void unset(Player player){
        modePlayerMap.remove(player);
    }
    public static synchronized String get(Player player){
        if (!modePlayerMap.containsKey(player)) return null;
        return modePlayerMap.get(player);
    }
    public static synchronized boolean contains(Player player){
        return modePlayerMap.containsKey(player);
    }
}

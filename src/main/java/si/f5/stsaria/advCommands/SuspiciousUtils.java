package si.f5.stsaria.advCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class SuspiciousUtils {
    public static boolean registerCommand(Command command) {
        SimpleCommandMap scm;
        if (Bukkit.getPluginCommand(command.getName()) != null)
            return false;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            scm = (SimpleCommandMap)field.get(pm);
        } catch (Exception ignore) {
            return false;
        }
        scm.register("usercmd", command);
        scm.registerServerAliases();
        return true;
    }
    public static void fail(){
        throw null;
    }
}

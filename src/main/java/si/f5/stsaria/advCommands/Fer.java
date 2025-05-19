package si.f5.stsaria.advCommands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.manager.EventFunctions;

import si.f5.stsaria.advCommands.variables.*;

public class Fer implements CommandExecutor {
    protected final JavaPlugin plugin;

    public Fer(String name){
        this.plugin = Main.getPlugin();

        PluginCommand command = this.plugin.getCommand(name);
        if(command != null){
            command.setExecutor(this);
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        boolean failedReturnBool = !GlobalVariables.get("env.debug").equals("true");
        UserFunction f = EventFunctions.get(EventType.ON_FER);
        if (f == null) return failedReturnBool;
        f.concat("event", new OnFer(commandSender, args));
        f.execute("");
        return true;
    }
}
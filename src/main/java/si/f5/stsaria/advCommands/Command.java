package si.f5.stsaria.advCommands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.advCommands.function.Function;

public class Command implements CommandExecutor {
    protected final JavaPlugin plugin;

    public Command(JavaPlugin plugin){
        this.plugin = plugin;

        PluginCommand command = this.plugin.getCommand("advCmd");
        if(command != null){
            command.setExecutor(this);
            command.setTabCompleter((TabCompleter) this);
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) return false;
        Function func = FunctionsManager.getFunction(args[0]);
        if (func == null){
            return false;
        }
        String r = func.execute(String.join(" ", args));
        commandSender.sendMessage(r);
        return true;
    }
}

package si.f5.stsaria.advCommands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

public class Command implements CommandExecutor {
    protected final JavaPlugin plugin;

    public Command(JavaPlugin plugin){
        this.plugin = plugin;

        PluginCommand command = this.plugin.getCommand("advCmd");
        if(command != null){
            command.setExecutor(this);
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) return false;
        Variables variables = new EmpVariables();
        GlobalVariables.getAll().forEach(variables::set);
        String line = Parser.variableSubstitution(variables, String.join(" ", args));
        Function func = FunctionsManager.get(args[0]);
        if (func == null){
            commandSender.sendMessage("error: func not found");
            return false;
        }
        String r = func.execute(line);
        if (r.isEmpty()) {
            commandSender.sendMessage("done");
        } else {
            commandSender.sendMessage(r);
        }
        return true;
    }
}

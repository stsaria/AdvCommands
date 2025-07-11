package si.f5.stsaria.cakeLang;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.cakeLang.function.Function;
import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.GlobalVariables;
import si.f5.stsaria.cakeLang.variables.NullV;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.Objects;

public class Interpreter implements CommandExecutor {
    protected final JavaPlugin plugin;

    public Interpreter(String name){
        this.plugin = Main.getPlugin();

        PluginCommand command = this.plugin.getCommand(name);
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
        Function func = Functions.get(args[0]);
        if (func == null){
            commandSender.sendMessage("error: func not found");
            return false;
        } else if (!line.matches(func.syntax())){
            commandSender.sendMessage("error: syntax");
            return false;
        }
        
        Variables r = func.execute(line, GlobalVariables.getRaw());
        
        if (r == null) r = new NullV();
        if (Objects.equals(r.get("resulttype"), "error") || Objects.equals(r.get("resulttype"), "oneresult")){
            commandSender.sendMessage(r.get("0"));
        }
        commandSender.sendMessage("done");
        return true;
    }
}
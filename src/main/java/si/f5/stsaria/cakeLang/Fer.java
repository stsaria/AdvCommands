package si.f5.stsaria.cakeLang;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.cakeLang.function.UserFunction;
import si.f5.stsaria.cakeLang.manager.EventFunctions;
import si.f5.stsaria.cakeLang.variables.GlobalVariables;
import si.f5.stsaria.cakeLang.variables.OnFer;

public class Fer {
    private final Command command;

    public Fer(String name){
        this.command = new Command(name) {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                boolean failedReturnBool = !GlobalVariables.get("env.debug").equals("true");
                UserFunction f = EventFunctions.get(EventType.ON_CMD);
                if (f == null) return failedReturnBool;
                f.getVariables().concat("event", new OnFer(commandSender, name, strings));
                f.execute("", null);
                return true;
            }
        };
    }
    public Command getCommand(){
        return this.command;
    }
}
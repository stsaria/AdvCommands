package si.f5.stsaria.advCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.manager.EventFunctions;

import si.f5.stsaria.advCommands.variables.*;

public class Fer {
    private final Command command;

    public Fer(String name){
        this.command = new Command(name) {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                boolean failedReturnBool = !GlobalVariables.get("env.debug").equals("true");
                UserFunction f = EventFunctions.get(EventType.ON_CMD);
                if (f == null) return failedReturnBool;
                f.concat("event", new OnFer(commandSender, name, strings));
                f.execute("");
                return true;
            }
        };
    }
    public Command getCommand(){
        return this.command;
    }
}
package si.f5.stsaria.advCommands.variables;

import org.bukkit.command.CommandSender;

public class OnFer extends Variables{
    public OnFer(CommandSender sender, String cmd, String[] args){
        this.concat("sender", new CommandSenderV(sender));
        this.set("cmd", cmd);
        this.set("argsstr", String.join(" ", args));
        for (int i = 0; i < args.length; i++){
            this.set("args."+i, args[i]);
        }
    }
}

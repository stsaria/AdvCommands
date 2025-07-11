package si.f5.stsaria.cakeLang.variables;

import org.bukkit.command.CommandSender;

public class CommandSenderV extends Variables{
    public CommandSenderV(CommandSender commandSender){
        this.set("sender.name", commandSender.getName());
        this.set("sender.isop", commandSender.isOp() ? "true" : "false");
    }
}
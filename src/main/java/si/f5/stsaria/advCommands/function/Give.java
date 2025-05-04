package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.ItemStackV;

public class Give implements Function{
    @Override
    public String syntax() {
        return "give [a-zA-Z0-9.]+ [a-zA-Z0-9_.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.contains(codeSplit[1])) return "error: itemStack var not found";
        Player player = Bukkit.getPlayer(codeSplit[2]);
        if (player == null) return "error: player not found";
        ItemStack itemStack = ItemStackV.toItemStack(codeSplit[1]);
        if (itemStack == null) return "error: generate itemStack";
        player.getInventory().addItem(itemStack);
        return "";
    }
}

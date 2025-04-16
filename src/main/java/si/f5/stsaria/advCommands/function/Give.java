package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.Objects;

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
        String varPrefix = codeSplit[1];
        ItemStack itemStack;
        try {
            String materialName = GlobalVariables.get(varPrefix + ".materialname").toUpperCase();
            itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(materialName)), Integer.parseInt(GlobalVariables.get(varPrefix + ".amount")));
        } catch (Exception ignore) {
            return "error: generate itemStack";
        } try {
            ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
            itemMeta.setDisplayName(GlobalVariables.get(varPrefix + ".displayname"));
            itemStack.setItemMeta(itemMeta);
            player.getInventory().addItem(itemStack);
        } catch (Exception ignore) {}
        return "";
    }
}

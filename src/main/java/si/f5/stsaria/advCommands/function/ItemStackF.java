package si.f5.stsaria.advCommands.function;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.ItemStackV;

import java.util.Objects;

public class ItemStackF implements Function{
    @Override
    public String syntax() {
        return "itemstack [a-zA-Z0-9.]+ [a-zA-Z0-9_]+ \\d+ .+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (Material.matchMaterial(codeSplit[2].toUpperCase()) == null) return "error: material not found";
        else if (!codeSplit[2].equals("air") && Integer.parseInt(codeSplit[3]) == 0) return "error: stack count must not be 0";
        ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(codeSplit[2].toUpperCase())), Integer.parseInt(codeSplit[3]));
        try {
            ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
            itemMeta.setDisplayName(code.replaceFirst("itemstack " + codeSplit[1] + " " + codeSplit[2] + " " + codeSplit[3] + " ", ""));
            itemStack.setItemMeta(itemMeta);
        } catch (Exception ignore) {}
        GlobalVariables.concat(codeSplit[1], new ItemStackV(itemStack));
        return "";
    }
}
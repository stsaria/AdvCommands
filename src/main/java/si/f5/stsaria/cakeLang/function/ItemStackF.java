package si.f5.stsaria.cakeLang.function;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.ItemStackV;
import si.f5.stsaria.cakeLang.variables.NullV;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.util.Objects;

public class ItemStackF extends Function {
    @Override
    public String syntax() {
        return "itemstack [a-zA-Z0-9_]+ \\d+ .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (Material.matchMaterial(codeSplit[1].toUpperCase()) == null) return new ErrorV("material not found");
        else if (!codeSplit[1].equals("air") && Integer.parseInt(codeSplit[2]) == 0) return new ErrorV("stack count must not be 0");
        ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(codeSplit[1].toUpperCase())), Integer.parseInt(codeSplit[2]));
        try {
            ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
            itemMeta.setDisplayName(code.replaceFirst("itemstack " + codeSplit[1] + " " + codeSplit[2] + " ", ""));
            itemStack.setItemMeta(itemMeta);
        } catch (Exception ignore) {
            return new NullV();
        }
        return new ItemStackV(itemStack);
    }
}
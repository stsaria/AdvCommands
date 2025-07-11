package si.f5.stsaria.cakeLang.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.ItemStackV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class Give extends Function {
    @Override
    public String syntax() {
        return "give [a-zA-Z0-9.]+ [a-zA-Z0-9_.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.contains(codeSplit[1])) return new ErrorV("itemStack var not found");
        Player player = Bukkit.getPlayer(codeSplit[2]);
        if (player == null) return new ErrorV("player not found");
        ItemStack itemStack = ItemStackV.toItemStack(codeSplit[1], variables);
        if (itemStack == null) return new ErrorV("generate itemStack");
        player.getInventory().addItem(itemStack);
        return null;
    }
}

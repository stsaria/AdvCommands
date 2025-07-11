package si.f5.stsaria.cakeLang.function;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.cakeLang.variables.*;

public class AddEnchant extends Function {
    @Override
    public String syntax() {
        return "addenchant [a-zA-Z0-9.]+ [a-z_]+ \\d+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.contains(codeSplit[1])) new ErrorV("source itemstack not found");
        Enchantment enchantment = Enchantment.getByName(codeSplit[2]);
        if (enchantment == null) return new ErrorV("unknown enchant");
        int level;
        try {level = Integer.parseInt(codeSplit[3]);}
        catch (NumberFormatException ignore) {return new ErrorV("cant cast string to int (level)");}
        ItemStack itemStack = ItemStackV.toItemStack(codeSplit[1], variables);
        if (itemStack == null) return new ErrorV("generate failed source itemStack");
        int len = GlobalVariables.length(codeSplit[1]+".enchants");
        variables.concat(codeSplit[1]+".enchants."+len, new Enchant(enchantment, level));
        return null;
    }
}

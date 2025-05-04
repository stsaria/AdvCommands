package si.f5.stsaria.advCommands.function;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.advCommands.variables.Enchant;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.ItemStackV;

public class AddEnchant implements Function{
    @Override
    public String syntax() {
        return "addenchant [a-zA-Z0-9.]+ [a-z_]+ \\d+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.contains(codeSplit[1])) return "error: source itemstack not found";
        Enchantment enchantment = Enchantment.getByName(codeSplit[2]);
        if (enchantment == null) return "error: unknown enchant";
        int level;
        try {level = Integer.parseInt(codeSplit[3]);}
        catch (NumberFormatException ignore) {return "error: cant cast string to int (level)";}
        ItemStack itemStack = ItemStackV.toItemStack(codeSplit[1]);
        if (itemStack == null) return "error: generate failed source itemStack";
        int len = GlobalVariables.length(codeSplit[1]+".enchants");
        new Enchant(enchantment, level).getVariableMap().forEach((n, v) ->
            GlobalVariables.set(codeSplit[1] + ".enchants." + len + "." + n, v)
        );
        return "";
    }
}

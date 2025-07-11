package si.f5.stsaria.cakeLang.variables;

import org.bukkit.enchantments.Enchantment;

import java.util.Map;
import java.util.Objects;

public class Enchant extends Variables {
    public Enchant(Enchantment enchantment, int level){
        this.set("name", enchantment.getName().toLowerCase());
        this.set("level", String.valueOf(level));
    }
    public static Map<Enchantment, Integer> toEnchantMap(String name){
        try{
            Enchantment enchantment = Enchantment.getByName(GlobalVariables.get(name+".name"));
            int level = Integer.parseInt(GlobalVariables.get(name+".level"));
            return Map.of(Objects.requireNonNull(enchantment), level);
        } catch (Exception ignore) {
            return null;
        }
    }
}

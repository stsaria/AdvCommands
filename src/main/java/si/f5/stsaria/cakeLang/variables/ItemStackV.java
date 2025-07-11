package si.f5.stsaria.cakeLang.variables;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ItemStackV extends Variables{
    public ItemStackV(ItemStack itemStack){
        try {
            this.set("displayname", Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName());
        } catch (Exception ignore) {
            this.set("displayname", "");
        }
        this.set("materialname", itemStack.getType().name().toLowerCase());
        this.set("amount", String.valueOf(itemStack.getAmount()));
        AtomicInteger i = new AtomicInteger();
        Objects.requireNonNull(itemStack.getItemMeta()).getEnchants().forEach((e, l) -> {
            this.set("enchants."+i.get()+".name", e.getName());
            this.set("enchants."+i.get()+".level", String.valueOf(l));
            i.getAndIncrement();
        });
    }
    public static ItemStack toItemStack(String name, Variables variables){
        try{
            ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(variables.get(name+".materialname").toUpperCase())), Integer.parseInt(variables.get(name+".amount")));
            try {
                ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
                itemMeta.setDisplayName(variables.get(name + ".displayname"));
                for (int i = 0; i < variables.length(name + ".enchants"); i++) {
                    Map<Enchantment, Integer> enchantMap = Enchant.toEnchantMap(variables.get(name + ".enchants." + i));
                    AtomicReference<Enchantment> enchantment = new AtomicReference<>();
                    AtomicInteger level = new AtomicInteger();
                    Objects.requireNonNull(enchantMap).forEach((e, l) -> {
                        enchantment.set(e);
                        level.set(l);
                    });
                    itemMeta.addEnchant(enchantment.get(), level.get(), true);
                }
                itemStack.setItemMeta(itemMeta);
            } catch (Exception ignore) {}
            return itemStack;
        } catch (Exception ignore) {
            return null;
        }
    }
}

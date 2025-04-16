package si.f5.stsaria.advCommands.variables;

import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemStackV extends Variables{
    public ItemStackV(ItemStack itemStack){
        try {
            this.set("displayname", Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName());
        } catch (Exception ignore) {
            this.set("displayname", "");
        }
        this.set("materialname", itemStack.getType().name().toLowerCase());
        this.set("amount", String.valueOf(itemStack.getAmount()));
    }
}

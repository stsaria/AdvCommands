package si.f5.stsaria.cakeLang;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.cakeLang.function.UserFunction;
import si.f5.stsaria.cakeLang.manager.EventFunctions;
import si.f5.stsaria.cakeLang.variables.OnClickGuiItemEvent;

import java.util.List;
import java.util.Objects;

public class Gui implements Listener {
    private final String name;
    private final Inventory inventory;
    public Gui(String name, String title, List<ItemStack> itemStacks){
        this.name = name;
        this.inventory = Bukkit.createInventory(null, itemStacks.size(), title);
        itemStacks.forEach(this.inventory::addItem);
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
    }
    public void open(HumanEntity human){
        human.openInventory(this.inventory);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if (!e.getInventory().equals(this.inventory)) return;
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null) return;
        else if (clickedItem.getType().isAir()) return;
        UserFunction f = EventFunctions.get(EventType.ON_CLICK_GUI_ITEM);
        if (f == null) return;
        f.getVariables().concat("event", new OnClickGuiItemEvent(this.name, e));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
}

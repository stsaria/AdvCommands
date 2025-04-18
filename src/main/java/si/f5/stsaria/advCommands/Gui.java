package si.f5.stsaria.advCommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.manager.EventFunctions;
import si.f5.stsaria.advCommands.variables.OnClickGuiItemEvent;

import java.util.List;

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
        e.setCancelled(true);
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;
        for (UserFunction f : EventFunctions.get(EventType.ON_CLICK_GUI_ITEM)) {
            new OnClickGuiItemEvent(this.name, e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
            f.execute("");
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e){
        if (e.getInventory().equals(this.inventory)) e.setCancelled(true);
    }
}

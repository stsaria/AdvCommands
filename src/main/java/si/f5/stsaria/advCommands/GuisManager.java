package si.f5.stsaria.advCommands;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class GuisManager {
    private static final Map<String, Gui> guiMap = new HashMap<>();
    public static synchronized int add(String name, String title, String itemStacksVarName){
        if (get(name) != null) return 1;
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.length(itemStacksVarName); i++){
            String varPrefix = itemStacksVarName+"."+i;
            if (!GlobalVariables.contains(varPrefix)) return 2;
            String materialName = GlobalVariables.get(varPrefix+".materialname").toUpperCase();
            ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(materialName)), Integer.parseInt(GlobalVariables.get(varPrefix+".amount")));
            try {
                ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
                itemMeta.setDisplayName(GlobalVariables.get(varPrefix + ".displayname"));
                itemStack.setItemMeta(itemMeta);
            } catch (Exception ignore) {}
            itemStacks.add(itemStack);
        }
        guiMap.put(name, new Gui(name, title, itemStacks));
        return 0;
    }
    public static synchronized Gui get(String name){
        AtomicReference<Gui> gui = new AtomicReference<>(null);
        guiMap.forEach((n, g) -> {
            if (n.equals(name)) gui.set(g);
        });
        return gui.get();
    }
    public static synchronized boolean contains(String name){
        return guiMap.containsKey(name);
    }
}

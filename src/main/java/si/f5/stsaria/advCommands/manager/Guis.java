package si.f5.stsaria.advCommands.manager;

import org.bukkit.inventory.ItemStack;
import si.f5.stsaria.advCommands.Gui;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.ItemStackV;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Guis {
    private static final Map<String, Gui> guiMap = new HashMap<>();
    public static synchronized int add(String name, String title, String itemStacksVarName){
        if (get(name) != null) return 1;
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < GlobalVariables.length(itemStacksVarName); i++){
            if (!GlobalVariables.contains(itemStacksVarName+"."+i)) return 2;
            ItemStack itemStack = ItemStackV.toItemStack(itemStacksVarName+"."+i);
            if (itemStack == null) return 3;
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

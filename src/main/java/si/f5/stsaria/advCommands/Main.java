package si.f5.stsaria.advCommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.manager.EventFunctions;
import si.f5.stsaria.advCommands.manager.FerCommands;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.manager.Libraries;
import si.f5.stsaria.advCommands.variables.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class Main extends BukkitRunnable implements Listener {
    private static final ArrayList<InfoRunFunc> runFunctions = new ArrayList<>();
    private static JavaPlugin plugin = null;
    private static Logger logger = null;
    private static Config config = null;
    public Main(JavaPlugin plugin){
        Main.plugin = plugin;
        Main.logger = plugin.getLogger();
        Main.config = new Config(plugin.getConfig());
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        Functions.initial();
        FerCommands.initial();
        Libraries.initial();
        new Interpreter("advcmd");

        this.runTaskTimer(plugin,0,0);
    }
    public static JavaPlugin getPlugin(){
        return Main.plugin;
    }
    public static Logger getLogger() {
        return Main.logger;
    }
    public static Config getConfig(){
        return Main.config;
    }
    public static synchronized void addRunFunction(InfoRunFunc info){
        runFunctions.add(info);
    }
    public static synchronized void solveSchedules() {
        runFunctions.forEach(info -> info.getFunction().execute(info.getCode(), info.getVariables()));
        runFunctions.clear();
    }
    @Override
    public void run() {
        solveSchedules();
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_KILL);
        if (f == null) return;
        new OnKillEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        f.execute("", null);
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_MOVE);
        if (f == null) return;
        new OnMoveEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_JOIN);
        if (f == null) return;
        new OnJoinEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        f.execute("", null);
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_PLACE_BLOCK);
        if (f == null) return;
        new OnPlaceBlockEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_BREAK_BLOCK);
        if (f == null) return;
        new OnBreakBlockEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_CHAT);
        if (f == null) return;
        new OnChatEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerItemClick(PlayerInteractEvent e){
        ItemStack clickedItem = e.getItem();
        if (clickedItem == null) return;
        else if (clickedItem.getType().isAir()) return;
        UserFunction f = EventFunctions.get(EventType.ON_CLICK_HAND_ITEM);
        if (f == null) return;
        new OnClickHandItemEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_LEAVE);
        if (f == null) return;
        new OnLeaveEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        f.execute("", null);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DROP);
        if (f == null) return;
        new OnDropEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!e.getInventory().getType().equals(InventoryType.CRAFTING)) return;
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null) return;
        else if (clickedItem.getType().isAir()) return;
        UserFunction f = EventFunctions.get(EventType.ON_CLICK_GUI_ITEM);
        if (f == null) return;
        new OnClickGuiItemEvent("default", e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DAMAGE);
        if (f == null) return;
        new OnDamageEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DAMAGE_BY_ENTITY);
        if (f == null) return;
        new OnDamageByEntityEvent(e).getVariableMap().forEach((n, v) -> f.getVariables().set("event." + n, v));
        if (Objects.equals(f.execute("", null).get("0"), "cancel")) e.setCancelled(true);
    }
}

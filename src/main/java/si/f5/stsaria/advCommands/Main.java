package si.f5.stsaria.advCommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
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
import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.manager.AppendFuncModePlayers;
import si.f5.stsaria.advCommands.manager.EventFunctions;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.*;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends BukkitRunnable implements Listener {
    private static final ArrayList<InfoRunFunc> runFunctions = new ArrayList<>();
    private static final ArrayList<InfoFunctionBlock> setFunctionBlockInfos = new ArrayList<>();
    private static JavaPlugin plugin = null;
    public Main(JavaPlugin plugin){
        Main.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        Functions.initial();
        new Command(plugin);

        this.runTaskTimer(plugin,0,0);
    }
    public static JavaPlugin getPlugin(){
        return Main.plugin;
    }
    public static synchronized void addRunFunction(InfoRunFunc info){
        runFunctions.add(info);
    }
    public static synchronized void addFunctionBlock(InfoFunctionBlock info){
        setFunctionBlockInfos.add(info);
    }
    public static synchronized void solveSchedules(){
        runFunctions.forEach(info -> info.getFunction().execute(info.getCode()));
        runFunctions.clear();
        setFunctionBlockInfos.forEach(info -> {
            Objects.requireNonNull(info.getLocation().getWorld()).setType(info.getLocation(), Material.COMMAND_BLOCK);
            Block block = Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(info.getLocation().getWorld()).getName())).getBlockAt(info.getLocation());
            CommandBlock commandBlock = (CommandBlock) block.getState();
            commandBlock.setCommand(info.getCommand());
            commandBlock.update();
            Location funcStartLocation = ((UserFunction) Functions.get(info.getFuncName())).getStartLocation();
            Functions.remove(info.getFuncName());
            Functions.add(info.getFuncName(), funcStartLocation);
        });
        setFunctionBlockInfos.clear();
    }
    @Override
    public void run() {
        solveSchedules();
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_KILL);
        if (f == null) return;
        new OnKillEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        f.execute("");
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_MOVE);
        if (f == null) return;
        new OnMoveEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_JOIN);
        if (f == null) return;
        new OnJoinEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        f.execute("");
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_PLACE_BLOCK);
        if (f == null) return;
        new OnPlaceBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_BREAK_BLOCK);
        if (f == null) return;
        new OnBreakBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if (AppendFuncModePlayers.contains(e.getPlayer())){
            e.setCancelled(true);
            if (e.getMessage().isEmpty()) return;
            String funcName = AppendFuncModePlayers.get(e.getPlayer());
            Function func = Functions.get(funcName);
            if (!(func instanceof UserFunction)) return;
            int length = ((UserFunction) func).size();
            Location funcStartLocation = ((UserFunction) func).getStartLocation();
            Location newLocation = new Location(Bukkit.getWorld(Objects.requireNonNull(funcStartLocation.getWorld()).getName()), funcStartLocation.getX(), funcStartLocation.getY()+length, funcStartLocation.getZ());
            if (newLocation.getY() > 256){
                e.getPlayer().sendMessage("error: height cant exceed 256");
                return;
            }
            addFunctionBlock(new InfoFunctionBlock(funcName, newLocation, e.getMessage()));
            e.getPlayer().sendMessage( (length+1)+" ".repeat(Math.max(0, 4 - String.valueOf(length+1).length()))+e.getMessage());
            return;
        }
        UserFunction f = EventFunctions.get(EventType.ON_CHAT);
        if (f == null) return;
        new OnChatEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerItemClick(PlayerInteractEvent e){
        ItemStack clickedItem = e.getItem();
        if (clickedItem == null) return;
        else if (clickedItem.getType().isAir()) return;
        UserFunction f = EventFunctions.get(EventType.ON_CLICK_HAND_ITEM);
        if (f == null) return;
        new OnClickHandItemEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_LEAVE);
        if (f == null) return;
        new OnLeaveEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        f.execute("");
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DROP);
        if (f == null) return;
        new OnDropEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(!e.getInventory().getType().equals(InventoryType.CRAFTING)) return;
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null) return;
        else if (clickedItem.getType().isAir()) return;
        UserFunction f = EventFunctions.get(EventType.ON_CLICK_GUI_ITEM);
        if (f == null) return;
        new OnClickGuiItemEvent("default", e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DAMAGE);
        if (f == null) return;
        new OnDamageEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e){
        UserFunction f = EventFunctions.get(EventType.ON_DAMAGE_BY_ENTITY);
        if (f == null) return;
        new OnDamageByEntityEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event." + n, v));
        if (f.execute("").equals("cancel")) e.setCancelled(true);
    }
}

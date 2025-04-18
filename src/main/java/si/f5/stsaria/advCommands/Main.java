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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
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
        for (UserFunction f : EventFunctions.get(EventType.ON_KILL)){
            new OnKillEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_MOVE)){
            new OnMoveEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_JOIN)){
            new OnJoinEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_PLACE_BLOCK)){
            new OnPlaceBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_BREAK_BLOCK)){
            new OnBreakBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
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
            addFunctionBlock(new InfoFunctionBlock(funcName, newLocation, e.getMessage()));
            e.getPlayer().sendMessage( (length+1)+" ".repeat(Math.max(0, 4 - String.valueOf(length+1).length()))+e.getMessage());
            return;
        }
        for (UserFunction f : EventFunctions.get(EventType.ON_CHAT)){
            new OnChatEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onPlayerItemClick(PlayerInteractEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_CLICK_HAND_ITEM)){
            new OnClickHandItemEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        for (UserFunction f : EventFunctions.get(EventType.ON_LEAVE)){
            new OnLeaveEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
}

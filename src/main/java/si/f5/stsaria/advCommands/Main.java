package si.f5.stsaria.advCommands;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.variables.*;

import java.util.ArrayList;

public class Main extends BukkitRunnable implements Listener {
    private static final ArrayList<String> runCommands = new ArrayList<>();
    public Main(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        FunctionsManager.initial();
        new Command(plugin);

        this.runTaskTimer(plugin,0,0);
    }
    public static synchronized void addRunCommands(String cmd){
        runCommands.add(cmd);
    }
    public static synchronized void runCommands(){
        runCommands.forEach(cmd -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd));
        runCommands.clear();
    }
    @Override
    public void run() {
        runCommands();
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_KILL)){
            new OnKillEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_MOVE)){
            new OnMoveEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_JOIN)){
            new OnJoinEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_PLACE_BLOCK)){
            new OnPlaceBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_BREAK_BLOCK)){
            new OnBreakBlockEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        for (UserFunction f : EventFunctionsManager.getEventFunctions(EventType.ON_CHAT)){
            new OnChatEvent(e).getVariableMap().forEach((n, v) -> f.setVariable("event."+n, v));
            f.execute("");
        }
    }
}

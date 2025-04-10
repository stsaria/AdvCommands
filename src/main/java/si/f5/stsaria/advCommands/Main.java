package si.f5.stsaria.advCommands;

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
    private static final ArrayList<InfoRunFunc> runFunctions = new ArrayList<>();
    public Main(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        FunctionsManager.initial();
        new Command(plugin);

        this.runTaskTimer(plugin,0,0);
    }
    public static synchronized void addRunFunctions(InfoRunFunc info){
        runFunctions.add(info);
    }
    public static synchronized void runFunctions(){
        runFunctions.forEach(info -> info.getFunction().execute(info.getCode()));
        runFunctions.clear();
    }
    @Override
    public void run() {
        runFunctions();
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

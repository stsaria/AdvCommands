package si.f5.stsaria.advCommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import si.f5.stsaria.advCommands.function.UserFunction;
import si.f5.stsaria.advCommands.variables.*;

public final class AdvCommands extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);

        FunctionsManager.initial();
        new Command(this);
    }

    @Override
    public void onDisable() {

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
}

package si.f5.stsaria.advCommands;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvCommands extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        new Main(this);
    }

    @Override
    public void onDisable() {

    }
}

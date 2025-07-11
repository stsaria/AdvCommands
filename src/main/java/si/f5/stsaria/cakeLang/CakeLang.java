package si.f5.stsaria.cakeLang;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CakeLang extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        new Main(this);
    }

    @Override
    public void onDisable() {

    }
}

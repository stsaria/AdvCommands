package si.f5.stsaria.advCommands;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdvCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        FunctionsManager.initial();
        new Command(this);
    }

    @Override
    public void onDisable() {

    }
}

package si.f5.stsaria.advCommands;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    private final FileConfiguration config;
    public Config(FileConfiguration config){
        this.config = config;

        config.addDefault("commands", new String[]{"testcmd"});
        config.options().copyDefaults(true);
        Main.getPlugin().saveConfig();
    }
    public List<String> getStringList(String path){
        return config.getStringList(path);
    }
}

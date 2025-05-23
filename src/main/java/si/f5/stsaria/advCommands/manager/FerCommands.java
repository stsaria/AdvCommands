package si.f5.stsaria.advCommands.manager;

import si.f5.stsaria.advCommands.Fer;
import si.f5.stsaria.advCommands.Main;
import si.f5.stsaria.advCommands.SuspiciousUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FerCommands {
    private static final Map<String, Fer> ferCommandMap = new HashMap<>();
    public static synchronized void initial(){
        ferCommandMap.put("fer", new Fer("fer"));

        List<String> commandStrs = Main.getConfig().getStringList("commands");
        commandStrs.forEach(cs -> {
            Fer fc = new Fer(cs);
            boolean r = SuspiciousUtils.registerCommand(fc.getCommand());
            if (!r) return;
            ferCommandMap.put(cs, fc);
        });
    }
    public static synchronized Map<String, Fer> getFerCommandMap(){
        return new HashMap<>(ferCommandMap);
    }
}

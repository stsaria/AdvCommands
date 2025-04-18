package si.f5.stsaria.advCommands;

import org.bukkit.Location;

public class InfoFunctionBlock {
    private final String funcName;
    private final Location location;
    private final String command;
    public InfoFunctionBlock(String funcName, Location location, String command){
        this.funcName = funcName;
        this.location = location;
        this.command = command;
    }

    public String getFuncName() {
        return funcName;
    }

    public Location getLocation() {
        return location;
    }

    public String getCommand() {
        return command;
    }
}

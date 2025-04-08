package si.f5.stsaria.advCommands.function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import si.f5.stsaria.advCommands.FunctionsManager;

public class DeclFunc implements Function{
    @Override
    public String syntax() {
        return "declFunc [a-zA-Z0-9]+ [a-zA-Z0-9_]+　[+-]?\\d+　[+-]?\\d+　[+-]?\\d+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        String[] codeSplit = code.split(" ");
        if (Bukkit.getWorld(codeSplit[2]) == null) return "error: world not found";
        int r = FunctionsManager.addUserFunction(codeSplit[1], new Location(Bukkit.getWorld(codeSplit[2]),
        Integer.parseInt(codeSplit[3]), Integer.parseInt(codeSplit[4]), Integer.parseInt(codeSplit[5])));
        if (r == 1){
            return "error: not command block";
        } else if (r == 2){
            return "error: func exists";
        }
        return "";
    }
}

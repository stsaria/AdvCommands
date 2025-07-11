package si.f5.stsaria.cakeLang.function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.Variables;

public class DeclFunc extends Function {
    @Override
    public String syntax() {
        return "declfunc [a-zA-Z0-9]+ [a-zA-Z0-9_]+ [+-]?\\d+ [+-]?\\d+ [+-]?\\d+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (Bukkit.getWorld(codeSplit[2]) == null) return new ErrorV("error: world not found");
        int r = Functions.add(codeSplit[1], new Location(Bukkit.getWorld(codeSplit[2]),
        Integer.parseInt(codeSplit[3]), Integer.parseInt(codeSplit[4]), Integer.parseInt(codeSplit[5])));
        if (r == 1){
            return new ErrorV("not command block");
        } else if (r == 2){
            return new ErrorV("func exists");
        }
        return null;
    }
}

package si.f5.stsaria.advCommands.function;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.FunctionsManager;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFunction implements Function{
    private final List<Block> blocks;
    private final String name;
    private final Variables variables = new EmpVariables();
    public UserFunction(String name, List<Block> blocks){
        this.name = name;
        this.blocks = blocks;
    }

    public synchronized void setVariable(String name, String variable) {
        variables.setVariable(name, variable);
    }
    public synchronized String getVariable(String name){
        return variables.getVariable(name);
    }

    @Override
    public String syntax() {
        return this.name;
    }

    @Override
    public synchronized String execute(String code) {
        for (int i = 1; i < code.split(" ").length; i++){
            this.setVariable("args."+(i-1), code.split(" ")[i]);
        }
        StringBuilder funcCode = new StringBuilder();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                funcCode.append(((CommandBlock) b.getState()).getCommand());
            }
        });
        int i = 0;
        for (String line : funcCode.toString().split("\n")){
            i++;
            while(line.contains("#randuuid#")){
                line = line.replaceFirst("#randuuid#", UUID.randomUUID().toString());
            }
            Matcher variablesMatcher = Pattern.compile(";(.*?);").matcher(line);
            while (variablesMatcher.find()){
                String variableName = variablesMatcher.group(1).replace(";", "");
                String variableValue = this.getVariable(variableName);
                if (variableValue == null) continue;
                line = line.replace(variablesMatcher.group(1), variableValue);
            }
            variablesMatcher = Pattern.compile("<(.*?)>").matcher(line);
            while (variablesMatcher.find()){
                String variableName = variablesMatcher.group(1).replace("<", "").replace(">", "");
                String variableValue = this.getVariable(variableName);
                if (variableValue == null) continue;
                line = line.replace(variablesMatcher.group(1), variableValue);
            }
            if (line.split(" ").length == 0) break;

            if (line.matches(new SetVar().syntax())){
                String[] lineSplit = line.split(" ");
                this.setVariable(lineSplit[1], line.replaceFirst("setVar "+lineSplit[1]+" ", ""));
                return "";
            }
            else {
                Function func = FunctionsManager.getFunction(line.split(" ")[0]);
                if (func == null) return "error: func not found";
                String r = func.execute(line);
                if (!r.isEmpty()) {
                    return "error: Line " + i + ": " + line + " - " + r;
                }
            }
        }
        return "";
    }
}

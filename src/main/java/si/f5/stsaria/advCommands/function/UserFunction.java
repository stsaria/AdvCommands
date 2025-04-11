package si.f5.stsaria.advCommands.function;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.FunctionsManager;
import si.f5.stsaria.advCommands.variables.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
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
        this.variables.setVariable(name, variable);
    }
    public synchronized String getVariable(String name){
        try{
            return String.valueOf(Integer.parseInt(name));
        } catch (Exception ignore) {
            return this.variables.getVariable(name);
        }
    }
    public synchronized void deleteVariable(String name){
        this.variables.deleteVariable(name);
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
        GlobalVariables.getVariables().forEach(this::setVariable);
        StringBuilder funcCode = new StringBuilder();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        new BlockV(this.blocks.getFirst()).getVariableMap().forEach((n, v) -> this.setVariable("funcFirstBlock."+n, v));
        int i = 0;
        for (String line : funcCode.toString().split("\n")){
            i++;
            new BlockV(this.blocks.get(i-1)).getVariableMap().forEach(((n, v) -> this.setVariable("funcNowLineBlock."+n, v)));
            if (line.isEmpty()) continue;
            while(line.contains("#randuuid#")){
                line = line.replaceFirst("#randuuid#", UUID.randomUUID().toString().replace("-", ""));
            }
            Matcher variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
            while (variablesMatcher.find()) {
                String g = variablesMatcher.group();
                String prefixRemovedG = g.replaceFirst("<", "").replaceAll(">$", "");
                String firstVarValue = this.getVariable(prefixRemovedG.split("[+\\-*/%=><^]")[0]);
                String secondVarValue = this.getVariable(prefixRemovedG.split("[+\\-*/%=><^]")[1]);
                if (firstVarValue == null || secondVarValue == null){
                    continue;
                }
                if (g.contains("=")) {
                    line = line.replace(g, firstVarValue.equals(secondVarValue) ? "true" : "false");
                } else {
                    try {
                        int firstVarValueInt = Integer.parseInt(firstVarValue);
                        int secondVarValueInt = Integer.parseInt(secondVarValue);
                        int ans = 0;
                        if (g.contains("+")) {
                            ans = firstVarValueInt + secondVarValueInt;
                        } else if (g.contains("-")) {
                            ans = firstVarValueInt - secondVarValueInt;
                        } else if (g.contains("*")) {
                            ans = firstVarValueInt * secondVarValueInt;
                        } else if (g.contains("/")) {
                            ans = firstVarValueInt / secondVarValueInt;
                        } else if (g.contains("%")) {
                            ans = firstVarValueInt % secondVarValueInt;
                        } else if (prefixRemovedG.contains("<")) {
                            line = line.replace(g, firstVarValueInt < secondVarValueInt ? "true" : "false");
                            variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
                            continue;
                        } else if (prefixRemovedG.contains(">")) {
                            line = line.replace(g, firstVarValueInt > secondVarValueInt ? "true" : "false");
                            variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
                            continue;
                        } else if (g.contains("^")) {
                            ans = (int) Math.pow(firstVarValueInt, secondVarValueInt);
                        }
                        line = line.replace(g, String.valueOf(ans));
                    } catch (Exception ignore) {
                        return "error: cant cast string to int";
                    }
                }
                variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+>").matcher(line);
            }
            variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+>").matcher(line);
            while (variablesMatcher.find()){
                String g = variablesMatcher.group();
                String variableValue = this.getVariable(g.replaceFirst("<", "").replaceAll(">$", ""));
                if (variableValue == null) continue;
                line = line.replace(g, variableValue);
                variablesMatcher = Pattern.compile("<[a-zA-Z0-9.]+>").matcher(line);
            }
            String[] lineSplit = line.split(" ");
            if (line.matches(new SetVar().syntax())){
                this.setVariable(lineSplit[1], line.replaceFirst("setVar "+lineSplit[1]+" ", ""));
            } else if (line.matches(new DelVar().syntax())){
                this.deleteVariable(lineSplit[1]);
            } else {
                Function func = FunctionsManager.getFunction(line.split(" ")[0]);
                if (func == null) return "error: func not found";
                String r = func.execute(line);
                if (r.startsWith("error: ")) {
                    return "error: Line " + i + ": " + line + " - " + r;
                }
            }
        }
        return "";
    }

    public synchronized String cat(){
        StringBuilder funcCode = new StringBuilder();
        AtomicInteger i = new AtomicInteger();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                if (((CommandBlock) b.getState()).getCommand().isEmpty()) return;
                funcCode.append(i.getAndIncrement()+1);
                funcCode.append(" ".repeat(Math.max(0, 4 - Arrays.stream(funcCode.toString().split("\n")).toList().getLast().length())));
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        return funcCode.toString();
    }
}

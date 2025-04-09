package si.f5.stsaria.advCommands.function;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.FunctionsManager;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.ArrayList;
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
        GlobalVariables.getVariables().forEach(this::setVariable);
        StringBuilder funcCode = new StringBuilder();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        int i = 0;
        for (String line : funcCode.toString().split("\n")){
            i++;
            if (line.isEmpty()) continue;
            while(line.contains("#randuuid#")){
                line = line.replaceFirst("#randuuid#", UUID.randomUUID().toString());
            }
            for (String[] prefix : new ArrayList<>(List.of(new String[]{";", ";"}, new String[]{"<", ">"}))) {
                Matcher variablesMatcher = Pattern.compile(prefix[0]+"[a-zA-Z0-9.+\\-*/%=><^]+"+prefix[1]).matcher(line);
                while (variablesMatcher.find()) {
                    String g = variablesMatcher.group();
                    String prefixRemovedG = g.replaceFirst(prefix[0], "").replace(prefix[1]+"$", "");
                    if (g.matches(prefix[0]+"[a-zA-Z0-9.]+[+\\-*/%=><^][a-zA-Z0-9.]+"+prefix[1])) {
                        String firstVarValue = this.getVariable(prefixRemovedG.split("\\+")[0]);
                        String secondVarValue = this.getVariable(prefixRemovedG.split("\\+")[1]);
                        if (firstVarValue == null || secondVarValue == null) continue;
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
                                } else if (g.contains("<")) {
                                    line = line.replace(g, firstVarValueInt < secondVarValueInt ? "true" : "false");
                                    continue;
                                } else if (g.contains(">")) {
                                    line = line.replace(g, firstVarValueInt > secondVarValueInt ? "true" : "false");
                                    continue;
                                } else if (g.contains("^")) {
                                    ans = (int) Math.pow(firstVarValueInt, secondVarValueInt);

                                }
                                line = line.replace(g, String.valueOf(ans));
                            } catch (Exception ignore) {
                                return "error: cant cast string to int";
                            }
                        }
                    } else {
                        String variableValue = this.getVariable(prefixRemovedG);
                        if (variableValue == null) continue;
                        line = line.replace(g, variableValue);
                    }
                }
            }
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

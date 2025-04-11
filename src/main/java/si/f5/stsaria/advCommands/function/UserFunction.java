package si.f5.stsaria.advCommands.function;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.FunctionsManager;
import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.variables.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
            line = Parser.variableSubstitution(variables, line);
            if (line.startsWith("error: ")) return line;
            String[] lineSplit = line.split(" ");
            if (line.matches(new SetVar().syntax())){
                this.setVariable(lineSplit[1], line.replaceFirst("setvar "+lineSplit[1]+" ", ""));
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

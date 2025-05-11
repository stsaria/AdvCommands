package si.f5.stsaria.advCommands.function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.variables.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BlocksFunction implements UserFunction{
    private final List<Block> blocks;
    private final String name;
    private final Variables variables = new EmpVariables();
    public BlocksFunction(String name, List<Block> blocks){
        this.name = name;
        this.blocks = blocks;
    }
    public synchronized String getName(){
        return this.name;
    }
    public synchronized Location getStartLocation(){
        return this.blocks.getFirst().getLocation();
    }
    public synchronized void setVariable(String name, String variable) {
        this.variables.set(name, variable);
    }
    @Override
    public String syntax() {
        return this.name;
    }

    @Override
    public synchronized String execute(String code) {
        for (int i = 1; i < code.split(" ").length; i++){
            this.variables.set("args."+(i-1), code.split(" ")[i]);
        }
        StringBuilder funcCode = new StringBuilder();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        new BlockV(this.blocks.getFirst()).getVariableMap().forEach((n, v) -> this.variables.set("funcFirstBlock."+n, v));
        int i = 0;
        boolean nextSkip = false;
        for (String line : funcCode.toString().split("\n")){
            GlobalVariables.getAll().forEach(this.variables::set);
            i++;
            if (nextSkip){
                nextSkip = false;
                continue;
            }
            new BlockV(this.blocks.get(i-1)).getVariableMap().forEach(((n, v) -> this.variables.set("funcNowLineBlock."+n, v)));
            if (line.isEmpty()) continue;
            line = Parser.variableSubstitution(this.variables, line);
            String[] lineSplit = line.split(" ");
            if (line.matches(new If().syntax())){
                if (lineSplit[1].equals("true")){
                    line = lineSplit[2];
                    lineSplit = new String[]{lineSplit[2]};
                } else if (lineSplit[1].equals("false")){
                    line = lineSplit[4];
                    lineSplit = new String[]{lineSplit[4]};
                }
            } else if (line.matches(new TrueIf().syntax())){
                if (lineSplit[1].equals("true")){
                    line = line.replaceFirst("trueif true ", "");
                    lineSplit = line.split(" ");
                }
            }

            if (!lineSplit[0].endsWith("G")){
                if (line.matches(new SetVar().syntax())){
                    this.variables.set(lineSplit[1], line.replaceFirst("setvar "+lineSplit[1]+" ", ""));
                    continue;
                } else if (line.matches(new DelVar().syntax())){
                    this.variables.delete(lineSplit[1]);
                    continue;
                } else if (line.matches(new CopyVar().syntax())){
                    this.variables.copy(lineSplit[1], lineSplit[2]);
                    continue;
                } else if (lineSplit[0].equals("silexit")){
                    return "";
                } else if (lineSplit[0].equals("skip")){
                    nextSkip = true;
                    continue;
                } else if (lineSplit[0].equals("cancel")){
                    return "cancel";
                }
            }
            Function func = Functions.get(line.split(" ")[0]);
            if (func == null) return "error: Line " + i + ": " + line + " - " + "func not found";
            String r = func.execute(line);
            if (r.startsWith("error: ")) {
                return "error: Line " + i + ": " + line + " - " + r;
            }
        }
        return "";
    }
    public synchronized String lines(){
        StringBuilder funcCode = new StringBuilder();
        this.blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                if (((CommandBlock) b.getState()).getCommand().isEmpty()) return;
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        return funcCode.toString();
    }
    public synchronized String formatedLines(){
        StringBuilder funcCode = new StringBuilder();
        AtomicInteger i = new AtomicInteger(0);
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
    public synchronized int size(){
        return this.blocks.size();
    }
}

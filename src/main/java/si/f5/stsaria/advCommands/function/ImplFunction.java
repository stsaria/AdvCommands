package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ImplFunction implements UserFunction{
    private final String name;
    private final String code;
    private final Variables variables = new EmpVariables();
    public ImplFunction(String name, String code){
        this.name = name;
        this.code = code;
    }
    public synchronized String getName(){
        return this.name;
    }
    public synchronized void setVariable(String name, String variable) {
        this.variables.set(name, variable);
    }
    public synchronized void concat(String rootName, Variables variables){
        this.variables.concat(rootName, variables);
    }
    @Override
    public String syntax() {
        return this.name;
    }

    @Override
    public synchronized String execute(String code) {
        this.variables.set("argsstr", code.replaceFirst(this.name+" ", ""));
        for (int i = 1; i < code.split(" ").length; i++){
            this.variables.set("args."+(i-1), code.split(" ")[i]);
        }
        int i = 0;
        boolean nextSkip = false;
        for (String line : this.code.split("\n")){
            GlobalVariables.getAll().forEach(this.variables::set);
            i++;
            if (nextSkip){
                nextSkip = false;
                continue;
            }
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
        return this.code;
    }
    public synchronized String formatedLines(){
        StringBuilder funcCode = new StringBuilder();
        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(this.code.split("\n")).forEach(l ->
            funcCode.append(i.getAndIncrement()+1)
            .append(" ".repeat(Math.max(0, 4 - Arrays.stream(funcCode.toString().split("\n")).toList().getLast().length())))
            .append(l).append("\n")
        );
        return funcCode.toString();
    }
    public synchronized int size(){
        return this.code.split("\n").length;
    }
}

package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.Main;
import si.f5.stsaria.advCommands.Parser;
import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.variables.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class UserFunction implements Function{
    private final String name;
    private final String code;
    private final Variables variables = new EmpVariables();
    public UserFunction(String name, String code){
        this.name = name;
        this.code = code;
    }
    public synchronized String getName(){
        return this.name;
    }
    public synchronized Variables getVariables(){
        return variables;
    }
    public String syntax() {
        return this.name;
    }

    @Override
    public synchronized Variables execute(String code, Variables variables) {
        boolean isEvent = this.variables.contains("event");
        Variables previousResult = new EmpVariables();
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
            switch (lineSplit[0]){
                case "saveresult":
                    if (Objects.equals(previousResult.get("resulttype"), "oneresult")) {
                        previousResult.getVariableMap().forEach((n, v) -> {
                            if (n.equals("0")) this.variables.set(lineSplit[1], v);
                            else if (n.startsWith("0.")) this.variables.set(lineSplit[1]+"."+n.replaceFirst("0.", ""), v);
                        });
                    } else {
                        this.variables.concat(lineSplit[1], previousResult);
                    }
                    continue;
                case "exit":
                    Variables result;
                    if (lineSplit.length == 2) {
                        result = new EmpVariables();
                        this.variables.getVariableMap().forEach((n, v) -> {
                            if (n.equals(lineSplit[1]) && n.startsWith(lineSplit[1]+".")) result.set(n.replace(lineSplit[1]+".", ""), v);
                        });
                        return result;
                    } else {
                        result = null;
                    }
                    return result;
                case "skip":
                    nextSkip = true;
                    continue;
                case "cancel":
                    return new OneResultV("cancel");
            }
            Function func = Functions.get(line.split(" ")[0]);
            if (func == null) return new ErrorV("Line " + i + ": " + line + " - " + "func not found");
            else if (!line.matches(func.syntax())) return new ErrorV("Line " + i + ": " + line + " - " + "syntax");
            Variables r = func.execute(line, this.getVariables());
            if (r == null) r = new NullV();
            previousResult = r;
            if (Objects.equals(r.get("resulttype"), "error")){
                if (isEvent) Main.getLogger().log(Level.SEVERE, "Error in event function! -> "+"Line " + i + ": " + line + " - " + r.get("0"));
                return new ErrorV("error: Line " + i + ": " + line + " - " + r.get("0"));
            }
        }
        return new NullV();
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
}

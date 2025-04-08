package si.f5.stsaria.advCommands.variables;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Variables {
    protected final Map<String, String> variableMap = new HashMap<>();
    public void setVariable(String name, String variable) {
        variableMap.put(name, variable);
    }
    public String getVariable(String name){
        AtomicReference<String> variable = new AtomicReference<>(null);
        variableMap.forEach((n, v) -> {if (n.equals(name)) variable.set(v);});
        return variable.get();
    }
    public Map<String, String> getVariableMap(){
        return new HashMap<>(this.variableMap);
    }
}

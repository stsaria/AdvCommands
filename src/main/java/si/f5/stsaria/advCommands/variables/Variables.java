package si.f5.stsaria.advCommands.variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Variables {
    protected final Map<String, String> variableMap = new HashMap<>();
    public void set(String name, String variable) {
        this.variableMap.put(name, variable);
    }
    public String get(String name){
        try{
            return String.valueOf(Long.parseLong(name));
        } catch (Exception ignore) {
            AtomicReference<String> variable = new AtomicReference<>(null);
            this.variableMap.forEach((n, v) -> {
                if (n.equals(name)) variable.set(v);
            });
            if (variable.get() == null){
                return name;
            }
            return variable.get();
        }
    }
    public Map<String, String> getVariableMap(){
        return new HashMap<>(this.variableMap);
    }
    public void delete(String name){
        ArrayList<String> deleteKeys = new ArrayList<>();
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name) || n.startsWith(name+".")) deleteKeys.add(n);
        });
        deleteKeys.forEach(this.variableMap::remove);
    }
    public void copy(String sourceName, String destinationName){
        Map<String, String> variableMapTemp = new HashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(sourceName+".")) variableMapTemp.put(destinationName+"."+(n.replaceFirst(sourceName+".", "")), v);
        });
        this.variableMap.putAll(variableMapTemp);
        if (this.get(sourceName) != null) this.variableMap.put(destinationName, this.get(sourceName));
    }
    public boolean contains(String name){
        AtomicBoolean found = new AtomicBoolean(false);
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name) || n.startsWith(name+".")) found.set(true);
        });
        return found.get();
    }
    public int length(String name){
        boolean found = true;
        int foundCount = 0;
        while (found){
            found = false;
            if (this.contains(name+"."+foundCount)){
                found = true;
                foundCount++;
            }
        }
        return foundCount;
    }
    public int size(String name){
        AtomicInteger foundCount = new AtomicInteger(0);
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(name+".")) foundCount.getAndIncrement();
        });
        return foundCount.get();
    }
}

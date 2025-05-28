package si.f5.stsaria.advCommands.variables;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Variables {
    protected final Map<String, String> variableMap = new HashMap<>();
    public void set(String name, String variable) {
        this.variableMap.put(name, variable);
    }
    public String get(String name){
        if (this.variableMap.get(name) == null){
            return name;
        }
        return this.variableMap.get(name);
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
    public boolean containsDirect(String name){
        AtomicBoolean found = new AtomicBoolean(false);
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name)) found.set(true);
        });
        return found.get();
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
    public Map<String, Object> toMap(String name){
        Map<String, Object> map = new HashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name)) map.put(Arrays.stream(n.split("\\.")).toList().getLast(), v);
            else if (n.startsWith(name + ".") && name.split("\\.").length+1 == n.split("\\.").length) map.put(Arrays.stream(n.split("\\.")).toList().getLast(), toMap(n));
        });
        return map;
    }
    public String toJson(String name){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(toMap(name));
        } catch (Exception ignore){
            return null;
        }
    }
    public Map<String, String> toOneLayerMap(String name){
        Map<String, String> map = new HashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(name + ".") && name.split("\\.").length + 1 == n.split("\\.").length)
                map.put(Arrays.stream(n.split("\\.")).toList().getLast(), v);
        });
        return map;
    }
    public void concat(String rootName, Variables variables){
        VariablesUtil.concat(this, rootName, variables);
    }
}

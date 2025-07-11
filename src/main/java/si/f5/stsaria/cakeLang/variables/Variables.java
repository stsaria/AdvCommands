package si.f5.stsaria.cakeLang.variables;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Variables {
    protected final Map<String, String> variableMap = new LinkedHashMap<>();
    public synchronized void set(String name, String variable) {
        this.variableMap.put(name, variable);
    }
    public synchronized String get(String name){
        String v = this.variableMap.get(name);
        return v == null ? name : v;
    }
    public synchronized Map<String, String> getVariableMap(){
        return new LinkedHashMap<>(this.variableMap);
    }
    public synchronized void delete(String name){
        ArrayList<String> deleteKeys = new ArrayList<>();
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name) || n.startsWith(name+".")) deleteKeys.add(n);
        });
        deleteKeys.forEach(this.variableMap::remove);
    }
    public synchronized void copy(String sourceName, String destinationName){
        Map<String, String> variableMapTemp = new LinkedHashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.equals(sourceName)) variableMapTemp.put(destinationName, v);
            else if (n.startsWith(sourceName+".")) variableMapTemp.put(destinationName+"."+n.replaceFirst(sourceName+".", ""), v);
        });
        this.variableMap.putAll(variableMapTemp);
        if (this.get(sourceName) != null) this.variableMap.put(destinationName, this.get(sourceName));
    }
    public synchronized boolean containsDirect(String name){
        AtomicBoolean found = new AtomicBoolean(false);
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name)) found.set(true);
        });
        return found.get();
    }
    public synchronized boolean contains(String name){
        AtomicBoolean found = new AtomicBoolean(false);
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name) || n.startsWith(name+".")) found.set(true);
        });
        return found.get();
    }
    public synchronized int length(String name){
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
    public synchronized int size(String name){
        AtomicInteger foundCount = new AtomicInteger(0);
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(name+".")) foundCount.getAndIncrement();
        });
        return foundCount.get();
    }
    public synchronized Map<String, Object> toMap(String name){
        Map<String, Object> map = new LinkedHashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.equals(name)) map.put(Arrays.stream(n.split("\\.")).toList().getLast(), v);
            else if (n.startsWith(name + ".") && name.split("\\.").length+1 == n.split("\\.").length) map.put(Arrays.stream(n.split("\\.")).toList().getLast(), toMap(n));
        });
        return map;
    }
    public synchronized String toJson(String name){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(toMap(name));
        } catch (Exception ignore){
            return null;
        }
    }
    public synchronized Map<String, String> toOneLayerMap(String name){
        Map<String, String> map = new LinkedHashMap<>();
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(name + ".") && name.split("\\.").length + 1 == n.split("\\.").length)
                map.put(Arrays.stream(n.split("\\.")).toList().getLast(), v);
        });
        return map;
    }
    public synchronized void concat(String rootName, Variables variables){
        VariablesUtil.concat(this, rootName, variables);
    }

    public synchronized boolean deepEqual(String leftName, String rightName){
        AtomicBoolean failed = new AtomicBoolean(false);
        if (!Objects.equals(this.variableMap.get(leftName), this.variableMap.get(rightName))) return false;
        this.variableMap.forEach((n, v) -> {
            if (n.startsWith(leftName+".")){
                if (!Objects.equals(this.variableMap.get(n), this.variableMap.get(rightName+n.replaceFirst(leftName, "")))) failed.set(true);
            } else if (n.startsWith(rightName+".")){
                if (!Objects.equals(this.variableMap.get(n), this.variableMap.get(leftName+n.replaceFirst(rightName, "")))) failed.set(true);
            }
        });
        return !failed.get();
    }

    public Variables clone(){
        try {
            return (Variables) super.clone();
        } catch (CloneNotSupportedException e){
            return null;
        }
    }
}

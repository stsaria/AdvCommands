package si.f5.stsaria.advCommands.variables;

import java.util.Map;

public class GlobalVariables {
    private static final Variables variables = new EmpVariables();
    public static synchronized void set(String name, String variable) {
        variables.set(name, variable);
    }
    public static synchronized Map<String, String> getAll(){
        return variables.getVariableMap();
    }
    public static synchronized String get(String name){
        return variables.get(name);
    }
    public static synchronized void delete(String name){
        variables.delete(name);
    }
    public static synchronized void copy(String sourceName, String destinationName){
        variables.copy(sourceName, destinationName);
    }
    public static synchronized boolean containsDirect(String name){
        return variables.containsDirect(name);
    }
    public static synchronized boolean contains(String name){
        return variables.contains(name);
    }
    public static synchronized int length(String name){
        return variables.length(name);
    }
    public static synchronized int size(String name){
        return variables.size(name);
    }
    public static synchronized Map<String, Object> toMap(String name){
        return variables.toMap(name);
    }
    public static synchronized String toJson(String name){
        return variables.toJson(name);
    }
    public static synchronized Map<String, String> toOneLayerMap(String name){
        return variables.toOneLayerMap(name);
    }
    public static synchronized void concat(String rootName, Variables variables){
        GlobalVariables.variables.concat(rootName, variables);
    }
    public static synchronized Variables getRaw(){
        return variables;
    }
}

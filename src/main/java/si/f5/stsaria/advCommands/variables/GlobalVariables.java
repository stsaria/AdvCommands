package si.f5.stsaria.advCommands.variables;

import java.util.Map;

public class GlobalVariables {
    private static final Variables variables = new EmpVariables();
    public static void setVariable(String name, String variable) {
        synchronized (GlobalVariables.class) {
            variables.setVariable(name, variable);
        }
    }
    public static String getVariable(String name){
        synchronized (GlobalVariables.class) {
            return variables.getVariable(name);
        }
    }
    public static Map<String, String> getVariables(){
        synchronized (GlobalVariables.class){
            return variables.getVariableMap();
        }
    }
}

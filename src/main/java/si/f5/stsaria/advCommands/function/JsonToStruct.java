package si.f5.stsaria.advCommands.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.f5.stsaria.advCommands.variables.EmpVariables;
import si.f5.stsaria.advCommands.variables.ErrorV;
import si.f5.stsaria.advCommands.variables.Variables;

import java.util.ArrayList;
import java.util.Map;

public class JsonToStruct implements Function{
    @Override
    public String syntax() {
        return "jsontostruct [a-zA-Z0-9.]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.containsDirect(codeSplit[2])) return new ErrorV("json string variable not found");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap;
        try{
            jsonMap = mapper.readValue(variables.get(codeSplit[2]), Map.class);
        } catch (Exception ignore){
            return new ErrorV("cant parse json");
        }
        return setVars(jsonMap, "");
    }

    private Variables setVars(Object obj, String baseVarName){
        Variables variables = new EmpVariables();
        if (obj instanceof Map) {
            ((Map<String, Object>) obj).forEach((k, v) -> {
                if (v instanceof Map) setVars(v, baseVarName + "." + k);
                else if (v instanceof ArrayList) setVars(v, baseVarName + "." + k);
                else variables.set(baseVarName + "." + k.replaceAll("[_-]", ""), String.valueOf(v).replaceAll("[_-]", ""));
            });
        } else if (obj instanceof ArrayList){
            ArrayList<Object> al = (ArrayList<Object>) obj;
            for (int i = 0; i < al.size(); i++){
                Object v = al.get(i);
                if (v instanceof Map) setVars(v, baseVarName+"."+i);
                else if (v instanceof ArrayList) setVars(v, baseVarName+"."+i);
                else variables.set(baseVarName+"."+i, String.valueOf(v).replaceAll("[_-]", ""));
            }
        } else {
            variables.set(baseVarName, String.valueOf(obj));
        }
        return variables;
    }
}

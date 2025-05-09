package si.f5.stsaria.advCommands.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.f5.stsaria.advCommands.variables.GlobalVariables;

import java.util.ArrayList;
import java.util.Map;

public class JsonToStruct implements Function{
    @Override
    public String syntax() {
        return "jsontostruct [a-zA-Z0-9.]+ (?s).*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap;
        try{
            jsonMap = mapper.readValue(code.replaceFirst("jsontostruct "+codeSplit[1]+" ", ""), Map.class);
        } catch (Exception ignore){
            return "error: cant parse json";
        }
        setVars(jsonMap, codeSplit[0]);
        return "";
    }

    private void setVars(Object obj, String baseVarName){
        if (obj instanceof Map) {
            ((Map<String, Object>) obj).forEach((k, v) -> {
                if (v instanceof Map) setVars(v, baseVarName + "." + k);
                else if (v instanceof ArrayList) setVars(v, baseVarName + "." + k);
                else GlobalVariables.set(baseVarName + "." + k, String.valueOf(v).replaceAll("[_-]", ""));
            });
        } else if (obj instanceof ArrayList){
            ArrayList<Object> al = (ArrayList<Object>) obj;
            for (int i = 0; i < al.size(); i++){
                Object v = al.get(i);
                if (v instanceof Map) setVars(v, baseVarName+"."+i);
                else if (v instanceof ArrayList) setVars(v, baseVarName+"."+i);
                else GlobalVariables.set(baseVarName+"."+i, String.valueOf(v).replaceAll("[_-]", ""));
            }
        } else {
            GlobalVariables.set(baseVarName, String.valueOf(obj));
        }
    }
}

package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.FunctionsManager;

public class For implements Function{
    @Override
    public String syntax() {
        return "for \\d+ \\.*\\";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        String[] codeSplit = code.split(" ");
        for (int i = 0; i < Integer.parseInt(codeSplit[1]); i++){
            Function func = FunctionsManager.getFunction(codeSplit[2].split(" ")[0]);
            if (func == null) return "error: func not found";
            String r = func.execute(code.replaceFirst("for "+codeSplit[1]+" ", ""));
            if (!r.startsWith("error: ")){
                return r;
            }
        }
        return "";
    }
}

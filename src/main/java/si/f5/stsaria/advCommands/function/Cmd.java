package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.Main;

public class Cmd implements Function{
    @Override
    public String syntax() {
        return "cmd .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())){
            return "error: syntax";
        }
        Main.addRunCommands(code.replaceFirst("cmd ", ""));
        return "";
    }
}

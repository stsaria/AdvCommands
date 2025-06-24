package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Guis;

public class DelGui implements Function{
    @Override
    public String syntax() {
        return "delgui [a-zA-Z0-9]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Guis.remove(codeSplit[1]);
        return "";
    }
}

package si.f5.stsaria.advCommands.function;

public class Nop implements Function{
    @Override
    public String syntax() {
        return "nop";
    }

    @Override
    public String execute(String code) {
        return "";
    }
}

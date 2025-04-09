package si.f5.stsaria.advCommands.function;

public class Exit implements Function{
    @Override
    public String syntax() {
        return "exit";
    }

    @Override
    public String execute(String code) {
        return "error: exited";
    }
}

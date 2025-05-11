package si.f5.stsaria.advCommands.function;

public interface UserFunction extends Function{
    String getName();
    void setVariable(String name, String variable);
    String lines();
    String formatedLines();
    int size();
}

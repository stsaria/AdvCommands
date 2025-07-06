package si.f5.stsaria.advCommands.variables;

public class ErrorV extends Variables{
    public ErrorV(String msg){
        this.set("0", msg);
        this.set("resulttype", "error");
    }
}

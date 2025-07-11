package si.f5.stsaria.cakeLang.variables;

public class ErrorV extends Variables{
    public ErrorV(String msg){
        this.set("0", msg);
        this.set("resulttype", "error");
    }
}

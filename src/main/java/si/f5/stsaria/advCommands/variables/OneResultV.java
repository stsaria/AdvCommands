package si.f5.stsaria.advCommands.variables;

public class OneResultV extends Variables{
    public OneResultV(String msg){
        this.set("0", msg);
        this.set("resulttype", "oneresult");
    }
}

package si.f5.stsaria.cakeLang.function;

import si.f5.stsaria.cakeLang.InfoRunFunc;
import si.f5.stsaria.cakeLang.Main;
import si.f5.stsaria.cakeLang.manager.Functions;
import si.f5.stsaria.cakeLang.variables.EmpVariables;
import si.f5.stsaria.cakeLang.variables.Variables;

public class WaitRun extends Function implements Runnable{
    private String code = "";
    private Variables variables = new EmpVariables();
    public WaitRun(){

    }
    public WaitRun(String code, Variables variables){
        this.code = code;
        this.variables = variables;
    }

    @Override
    public String syntax() {
        return "waitrun \\d+ .+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        new Thread(new WaitRun(code, variables.clone())).start();
        return null;
    }

    @Override
    public void run() {
        String[] codeSplit = code.split(" ");
        try {
            Thread.sleep(Integer.parseInt(codeSplit[1]));
        } catch (InterruptedException ignore){}
        Function func = Functions.get(codeSplit[2]);
        if (func == null) return;
        else if (!this.code.matches(func.syntax())) return;
        Main.addRunFunction(new InfoRunFunc(func, this.code.replaceFirst("waitrun "+codeSplit[1]+" ", ""), this.variables));
    }
}

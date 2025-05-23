package si.f5.stsaria.advCommands.function;

import si.f5.stsaria.advCommands.manager.Functions;
import si.f5.stsaria.advCommands.InfoRunFunc;
import si.f5.stsaria.advCommands.Main;

public class WaitRun implements Function, Runnable{
    private String code = "";
    public WaitRun(){

    }
    public WaitRun(String code){
        this.code = code;
    }

    @Override
    public String syntax() {
        return "waitrun \\d+ .*";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        new Thread(new WaitRun(code)).start();
        return "";
    }

    @Override
    public void run() {
        String[] codeSplit = code.split(" ");
        try {
            Thread.sleep(Integer.parseInt(codeSplit[1]));
        } catch (InterruptedException ignore){}
        Function func = Functions.get(codeSplit[2].split(" ")[0]);
        if (func == null) return;
        Main.addRunFunction(new InfoRunFunc(func, code.replaceFirst("waitrun "+codeSplit[1]+" ", "")));
    }
}

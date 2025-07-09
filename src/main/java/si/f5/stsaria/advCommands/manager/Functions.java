package si.f5.stsaria.advCommands.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import si.f5.stsaria.advCommands.function.*;

import java.util.*;

public class Functions{
    private static final Map<String, Function> functionMap = new HashMap<>();
    public static synchronized void initial(){
        functionMap.put("for", new For());
        functionMap.put("declfunc", new DeclFunc());
        functionMap.put("setvar", new SetVar());
        functionMap.put("setvarG", new SetVar());
        functionMap.put("cmd", new Cmd());
        functionMap.put("seteventfunc", new SetEventFunc());
        functionMap.put("if", new If());
        functionMap.put("nop", new Nop());
        functionMap.put("delvar", new DelVar());
        functionMap.put("delvarG", new DelVar());
        functionMap.put("catfunc", new CatFunc());
        functionMap.put("waitrun", new WaitRun());
        functionMap.put("randint", new RandInt());
        functionMap.put("copyvar", new CopyVar());
        functionMap.put("copyvarG", new CopyVar());
        functionMap.put("length", new Length());
        functionMap.put("itemstack", new ItemStackF());
        functionMap.put("newgui", new NewGui());
        functionMap.put("opengui", new OpenGui());
        functionMap.put("give", new Give());
        functionMap.put("trueif", new TrueIf());
        functionMap.put("bungeemove", new BungeeMove());
        functionMap.put("size", new Size());
        functionMap.put("teams", new Teams());
        functionMap.put("players", new Players());
        functionMap.put("strtolist", new StrToList());
        functionMap.put("shuffled", new Shuffled());
        functionMap.put("addenchant", new AddEnchant());
        functionMap.put("split", new Split());
        functionMap.put("jsontostruct", new JsonToStruct());
        functionMap.put("httpget", new HttpGet());
        functionMap.put("httppost", new HttpPost());
        functionMap.put("replace", new Replace());
        functionMap.put("structtojson", new StructToJson());
        functionMap.put("variables", new VariablesF());
        functionMap.put("exportfunc", new ExportFunc());
        functionMap.put("importfunc", new ImportFunc());
        functionMap.put("toint", new ToInt());
        functionMap.put("getname", new GetName());
        functionMap.put("delgui", new DelGui());
        functionMap.put("closegui", new CloseGui());
        functionMap.put("runinglobal", new RunInGlobal());
        functionMap.put("localtoglobal", new LocalToGlobal());
        functionMap.put("globaltolocal", new GlobalToLocal());
        functionMap.put("deepequal", new DeepEqual());
        functionMap.put("ismatch", new IsMatch());
        functionMap.put("skip", new Skip());
    }
    public static synchronized int add(String name, Location location){
        if (!location.getBlock().getType().equals(Material.COMMAND_BLOCK)) return 1;
        else if (functionMap.get(name) != null) return 2;
        ArrayList<Block> blocks = new ArrayList<>(List.of(location.getBlock()));
        while (true) {
            Block b = Objects.requireNonNull(location.getWorld()).getBlockAt(
                location.getBlockX(), blocks.getLast().getLocation().getBlockY() + 1, location.getBlockZ()
            );
            if (b.getType().equals(Material.COMMAND_BLOCK)){
               blocks.add(b);
            } else {
                break;
            }
        }
        StringBuilder funcCode = new StringBuilder();
        blocks.forEach(b -> {
            if (b.getType().equals(Material.COMMAND_BLOCK)){
                funcCode.append(((CommandBlock) b.getState()).getCommand()).append("\n");
            }
        });
        functionMap.put(name, new UserFunction(name, funcCode.toString()));
        return 0;
    }
    public static synchronized int addDirect(String name, String code){
        if (functionMap.get(name) != null) return 1;
        functionMap.put(name, new UserFunction(name, code));
        return 0;
    }
    public static synchronized void remove(String name){
        functionMap.remove(name);
    }
    public static synchronized Function get(String name){
        Function f = functionMap.get(name);
        return f == null ? null : f.clone();
    }
}

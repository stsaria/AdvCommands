package si.f5.stsaria.advCommands.function;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import si.f5.stsaria.advCommands.Main;

public class BungeeMove implements Function{
    @Override
    public String syntax() {
        return "bungeemove [a-zA-Z0-9\\-]+ [a-zA-Z0-9_.]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        Player player = Bukkit.getPlayer(codeSplit[2]);
        if (player == null) return "error: player not found";
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(Main.getPlugin(), "BungeeCord");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(codeSplit[1]);
        player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
        return "";
    }
}

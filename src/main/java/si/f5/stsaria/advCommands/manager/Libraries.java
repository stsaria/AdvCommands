package si.f5.stsaria.advCommands.manager;

import si.f5.stsaria.advCommands.InJarFileUtil;
import si.f5.stsaria.advCommands.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libraries {
    private static final Map<String, File> libraryMap = new HashMap<>();

    private static File getLibsDir(){
        return new File(Main.getPlugin().getDataFolder().getAbsoluteFile().getName()+"/libs");
    }
    private static void copyFromResources(String name){
        boolean isSuccess = InJarFileUtil.copyResourcesFileToLocalFile("/libs/"+name+".func", getLibsDir().getName()+"/"+name+".func");
        Logger logger = Main.getLogger();
        if (isSuccess) {
            logger.log(Level.INFO, "Loaded initial library \""+name+"\"");
        } else {
            logger.log(Level.WARNING, "Cannot load initial library! \""+name+"\"");
        }
    }
    private static synchronized void getAndPutFiles(){
        File workingDir = getLibsDir();
        File[] libraryFiles = workingDir.listFiles();
        for (File f : Objects.requireNonNull(libraryFiles)){
            if (!f.getName().endsWith(".func")) continue;
            libraryMap.put(f.getName().replaceAll(".func$", ""), f);
        }
    }
    private static synchronized void registers(){
        libraryMap.forEach((n, f) -> {
            try {
                Functions.addDirect(n,  String.join("\n", Files.readAllLines(f.toPath())));
            } catch (IOException ignore) {}
        });
    }
    public static synchronized void initial(){
        try {
            if (!getLibsDir().isDirectory()) Files.createDirectory(getLibsDir().toPath());
        } catch (IOException ignore) {}

        copyFromResources("abc");

        getAndPutFiles();
        registers();
    }
}

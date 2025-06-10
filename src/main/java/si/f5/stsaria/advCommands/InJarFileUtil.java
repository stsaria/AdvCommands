package si.f5.stsaria.advCommands;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class InJarFileUtil {
    public static boolean copyResourcesFileToLocalFile(String fromFileName, String toFileName){
        InputStream fromFileStream = InJarFileUtil.class.getResourceAsStream("/"+fromFileName);
        if (fromFileStream == null) return false;
        boolean isSuccess = false;
        try{
            OutputStream toFileStream = new FileOutputStream(toFileName);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fromFileStream.read(buffer)) != -1) {
                toFileStream.write(buffer, 0, bytesRead);
            }
            toFileStream.close();
            isSuccess = true;
        } catch (Exception ignore) {}
        try{
            fromFileStream.close();
        } catch (Exception ignore){}
        return isSuccess;
    }
}
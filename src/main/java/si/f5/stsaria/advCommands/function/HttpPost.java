package si.f5.stsaria.advCommands.function;

import org.apache.commons.io.IOUtils;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.HttpResponse;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpPost implements Function{
    @Override
    public String syntax() {
        return "httppost [a-zA-Z0-9.]+ [a-zA-Z0-9.]+ https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        if (!GlobalVariables.contains(codeSplit[2])) return "error: request properties variable not found.";
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(codeSplit[3]).toURL().openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("user-agent", "AdvCommandsHTTP.JAVA");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            StringBuilder parameter = new StringBuilder();
            GlobalVariables.toOneLayerMap(codeSplit[2]).forEach((k, v) -> {
                k = k.replace("&", "%26");
                v = v.replace("&", "%26");
                parameter.append(k).append("=").append(v).append("&");
            });
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(parameter.toString().replaceFirst("&$", ""));
            writer.flush();
            writer.close();
            int responseCode = connection.getResponseCode();
            String newUrl;
            URI tempUrl;
            while (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                newUrl = connection.getHeaderField("Location");
                tempUrl = URI.create(newUrl);
                if (!tempUrl.isAbsolute()){
                    tempUrl = URI.create(codeSplit[2]).resolve(newUrl);
                }
                connection = (HttpURLConnection) tempUrl.toURL().openConnection();
                connection.setRequestProperty("user-agent", "AdvCommandsHTTP.JAVA");
                connection.setInstanceFollowRedirects(false);
                responseCode = connection.getResponseCode();
            }
            GlobalVariables.concat(codeSplit[1], new HttpResponse(responseCode, IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8).replace("<", "&lt").replace(">", "&gt")));
        } catch (Exception ignore) {
            return "error: failed communication";
        }
        return "";
    }
}

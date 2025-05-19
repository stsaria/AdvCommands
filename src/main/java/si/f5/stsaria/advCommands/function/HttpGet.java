package si.f5.stsaria.advCommands.function;

import org.apache.commons.io.IOUtils;
import si.f5.stsaria.advCommands.variables.GlobalVariables;
import si.f5.stsaria.advCommands.variables.HttpResponse;

import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpGet implements Function{
    @Override
    public String syntax() {
        return "httpget [a-zA-Z0-9.]+ https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
    }

    @Override
    public String execute(String code) {
        if (!code.matches(syntax())) return "error: syntax";
        String[] codeSplit = code.split(" ");
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(codeSplit[2]).toURL().openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("user-agent", "AdvCommandsHTTP.JAVA");
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

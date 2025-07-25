package si.f5.stsaria.cakeLang.function;

import org.apache.commons.io.IOUtils;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.HttpResponse;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpGet extends Function {
    @Override
    public String syntax() {
        return "httpget https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        Variables result;
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(codeSplit[1]).toURL().openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("user-agent", "CakeLangHTTP.JAVA");
            int responseCode = connection.getResponseCode();
            String newUrl;
            URI tempUrl;
            while (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                newUrl = connection.getHeaderField("Location");
                tempUrl = URI.create(newUrl);
                if (!tempUrl.isAbsolute()){
                    tempUrl = URI.create(codeSplit[1]).resolve(newUrl);
                }
                connection = (HttpURLConnection) tempUrl.toURL().openConnection();
                connection.setRequestProperty("user-agent", "CakeLangHTTP.JAVA");
                connection.setInstanceFollowRedirects(false);
                responseCode = connection.getResponseCode();
            }
            result = new HttpResponse(responseCode, IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8).replace("<", "&lt").replace(">", "&gt"));
        } catch (Exception ignore) {
            return new ErrorV("failed communication");
        }
        return result;
    }
}

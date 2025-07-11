package si.f5.stsaria.cakeLang.function;

import org.apache.commons.io.IOUtils;
import si.f5.stsaria.cakeLang.variables.ErrorV;
import si.f5.stsaria.cakeLang.variables.HttpResponse;
import si.f5.stsaria.cakeLang.variables.Variables;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class HttpPost extends Function {
    @Override
    public String syntax() {
        return "httppost [a-zA-Z0-9.]+ https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
    }

    @Override
    public Variables execute(String code, Variables variables) {
        String[] codeSplit = code.split(" ");
        if (!variables.contains(codeSplit[1])) return new ErrorV("request properties variable not found");
        Variables result;
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(codeSplit[2]).toURL().openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("user-agent", "CakeLangHTTP.JAVA");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            StringBuilder parameter = new StringBuilder();
            variables.toOneLayerMap(codeSplit[1]).forEach((k, v) -> {
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

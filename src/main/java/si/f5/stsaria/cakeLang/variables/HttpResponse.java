package si.f5.stsaria.cakeLang.variables;

public class HttpResponse extends Variables {
    public HttpResponse(int statusCode, String content){
        this.set("statuscode", String.valueOf(statusCode));
        this.set("content", content);
    }
}

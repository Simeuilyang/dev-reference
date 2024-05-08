package Java.Jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.JsonObject;

public class HttpClientTest {
    String uri;

    public HttpClientTest(String uri) {
        this.uri = uri;
    }

    public void callOut(String data) {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            // 요청 생성
            Request req = httpClient.newRequest(uri).method(HttpMethod.GET);

            // 요청 데이터 생성 및 추가
            JsonObject reqJson = new JsonObject();
            reqJson.addProperty("result", data);

            StringContentProvider reqContent = new StringContentProvider(reqJson.toString());
            req.content(reqContent);

            // 호출
            req.send();

            httpClient.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

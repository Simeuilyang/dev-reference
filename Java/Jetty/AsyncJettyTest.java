package Java.Jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.FutureResponseListener;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AsyncJettyTest extends Thread{
    Gson gson = new Gson();
    String uri;

    public AsyncJettyTest(String uri) {
        this.uri = uri;
    }

    // thread 시작 시, 호출됨
    public void run() {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            Request req = httpClient.newRequest(uri).method(HttpMethod.GET);
            
            // 비동기적 http 요청 시 사용. 응답을 받을 때까지 대기
            FutureResponseListener listener = new FutureResponseListener(req);
            req.send(listener);

            while (true) {
                if (listener.isDone()) {
                    // 응답 데이터
                    ContentResponse resp = listener.get();
                    JsonObject respContent = gson.fromJson(resp.getContentAsString(), JsonObject.class);

                    // 로직 수행

                    // 재호출
                    req = httpClient.newRequest(uri).method(HttpMethod.GET);
                    listener = new FutureResponseListener(req);
                    req.send(listener);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

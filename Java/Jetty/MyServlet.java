package Java.Jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

public class MyServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);
        String header = getHeader(req);

        ContentResponse contentRes = sendRequest(uri, HttpMethod.GET, header);

        responseServlet(resp, contentRes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUri(req);
        String header = getHeader(req);

        ContentResponse contentRes = sendRequest(uri, HttpMethod.POST, header);

        responseServlet(resp, contentRes);
    }
    
    protected void responseServlet(HttpServletResponse res, ContentResponse contentRes) throws IOException {
        int status = contentRes.getStatus();

        res.setStatus(status);  // 호출 결과 status 그대로 전달
        res.setHeader("Content-type", contentRes.getHeaders().get("Content-type")); // 응답 Content-type header 그대로 전달
        res.getWriter().write(contentRes.getContentAsString()); // 응답 데이터 그대로 전달
        res.flushBuffer();
    }

    protected ContentResponse sendRequest(String uri, HttpMethod method, String header) {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            Request req = httpClient.newRequest(uri).method(method);
            req.header("Content-type", header);

            ContentResponse contentRes = req.send();

            return contentRes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String getUri(HttpServletRequest req) {
        
        String path = req.getPathInfo();    // path 정보 추출
        String url = "/test";   // 이후 호출할 url
        String query = req.getQueryString();    // query 정보 추출
        String uri = url + path + (query == null? "" : "?" + query);

        return uri;        
    }
    
    protected String getHeader(HttpServletRequest req) {
        // Content-type header 추출
        return req.getHeader("Content-type");
    }
}

package Java.Jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyServer {
    
    public MyServer(int port) {
        try {
            start(port);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void start(int port) {
        // jetty server 정의
        Server server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("127.0.0.1");  // host 설정
        http.setPort(port);     // port 설정
        server.addConnector(http);

        // servlet handler 설정
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MyServlet.class, "/*");
        server.setHandler(servletHandler);

        try {
            // server 구동
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

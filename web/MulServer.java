import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class MulServer {

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/mul", new MulHandler());

        server.setExecutor(null);

        server.start();

        System.out.println("Multiplication Server running on port 8080");
    }

    static class MulHandler implements HttpHandler {

        public void handle(HttpExchange ex) throws IOException {

            String query = ex.getRequestURI().getQuery();

            Map<String, String> map = parseQuery(query);

            double a = Double.parseDouble(map.get("a"));
            double b = Double.parseDouble(map.get("b"));

            double result = a * b;

            String response = "Multiplication = " + result;

            ex.sendResponseHeaders(200, response.length());

            OutputStream os = ex.getResponseBody();

            os.write(response.getBytes());

            os.close();
        }
    }

    static Map<String, String> parseQuery(String query) {

        Map<String, String> map = new HashMap<String, String>();

        String[] pairs = query.split("&");

        for (String pair : pairs) {

            String[] parts = pair.split("=");

            map.put(parts[0], parts[1]);
        }

        return map;
    }
}
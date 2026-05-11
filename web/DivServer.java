import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class DivServer {

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/div", new DivHandler());

        server.setExecutor(null);

        server.start();

        System.out.println("Division Server running on port 8080");
    }

    static class DivHandler implements HttpHandler {

        public void handle(HttpExchange ex) throws IOException {

            String query = ex.getRequestURI().getQuery();

            Map<String, String> map = parseQuery(query);

            double a = Double.parseDouble(map.get("a"));
            double b = Double.parseDouble(map.get("b"));

            String response;

            // Check division by zero
            if (b == 0) {

                response = "Error: Division by zero";

                ex.sendResponseHeaders(400, response.length());

            } else {

                double result = a / b;

                response = "Division = " + result;

                ex.sendResponseHeaders(200, response.length());
            }

            OutputStream os = ex.getResponseBody();

            os.write(response.getBytes());

            os.close();
        }
    }

    static Map<String, String> parseQuery(String query) {

        Map<String, String> map =
                new HashMap<String, String>();

        String[] pairs = query.split("&");

        for (String pair : pairs) {

            String[] parts = pair.split("=");

            map.put(parts[0], parts[1]);
        }

        return map;
    }
}
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SignupServer {

    static Map<String, String> users =
            new HashMap<String, String>();

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/signup", new SignupHandler());

        server.setExecutor(null);

        server.start();

        System.out.println("Signup Server running on port 8080");
    }

    static class SignupHandler implements HttpHandler {

        public void handle(HttpExchange ex) throws IOException {

            System.out.println("Request received");

            if (!ex.getRequestMethod().equalsIgnoreCase("POST")) {

                String response = "Only POST method allowed";

                ex.sendResponseHeaders(405, response.length());

                OutputStream os = ex.getResponseBody();

                os.write(response.getBytes());

                os.close();

                return;
            }

            // Read complete request body
            String body = readAll(ex.getRequestBody());

            System.out.println("Body: " + body);

            Map<String, String> map = parseQuery(body);

            String name = map.get("name");
            String email = map.get("email");
            String password = map.get("password");

            String response;

            if (name == null || email == null || password == null) {

                response = "Missing fields";

                ex.sendResponseHeaders(400, response.length());

            } else {

                users.put(email, name + "|" + password);

                response =
                        "Signup Successful\n"
                        + "Name: " + name + "\n"
                        + "Email: " + email;

                ex.sendResponseHeaders(200, response.length());
            }

            OutputStream os = ex.getResponseBody();

            os.write(response.getBytes());

            os.close();
        }
    }

    static String readAll(InputStream in)
            throws IOException {

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        int n;

        while ((n = in.read(buffer)) != -1) {

            out.write(buffer, 0, n);
        }

        return out.toString("UTF-8");
    }

    static Map<String, String> parseQuery(String query)
            throws IOException {

        Map<String, String> map =
                new HashMap<String, String>();

        String[] pairs = query.split("&");

        for (String pair : pairs) {

            String[] parts = pair.split("=");

            String key =
                    URLDecoder.decode(parts[0], "UTF-8");

            String value =
                    URLDecoder.decode(parts[1], "UTF-8");

            map.put(key, value);
        }

        return map;
    }
}
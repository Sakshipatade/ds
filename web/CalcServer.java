import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

// A simple HTTP web service offering calculator + signup endpoints.
// Uses only the JDK (works on Java 8).
public class CalcServer {

    public static void main(String[] args) throws IOException {
        // Create the HTTP server on port 8080.
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Register one handler per endpoint.
        server.createContext("/add", new MathHandler("add"));
        server.createContext("/sub", new MathHandler("sub"));
        server.createContext("/mul", new MathHandler("mul"));
        server.createContext("/div", new MathHandler("div"));
        server.createContext("/signup", new SignupHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Web service running on http://localhost:8080");
        System.out.println("Endpoints: /add  /sub  /mul  /div  /signup");
    }

    // ---- Practicals 19..22: arithmetic ----
    static class MathHandler implements HttpHandler {
        private final String op;

        MathHandler(String op) {
            this.op = op;
        }

        public void handle(HttpExchange ex) throws IOException {
            // Read the query string (e.g. "a=2&b=3").
            Map<String, String> q = parseQuery(ex.getRequestURI().getRawQuery());
            String aStr = q.get("a");
            String bStr = q.get("b");
            if (aStr == null) aStr = "0";
            if (bStr == null) bStr = "0";

            try {
                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double r = 0;
                if (op.equals("add")) {
                    r = a + b;
                } else if (op.equals("sub")) {
                    r = a - b;
                } else if (op.equals("mul")) {
                    r = a * b;
                } else if (op.equals("div")) {
                    if (b == 0) {
                        send(ex, 400, "Error: division by zero");
                        return;
                    }
                    r = a / b;
                }
                send(ex, 200, op + "(" + a + ", " + b + ") = " + r);
            } catch (NumberFormatException e) {
                send(ex, 400, "Error: a and b must be numbers");
            }
        }
    }

    // ---- Practical 23: sign-up ----
    static class SignupHandler implements HttpHandler {
        // In-memory "database" of users (email -> "name|password").
        static final Map<String, String> users = new HashMap<String, String>();

        public void handle(HttpExchange ex) throws IOException {
            // Only POST is allowed.
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                send(ex, 405, "Use POST with name=...&email=...&password=...");
                return;
            }

            // Read the request body as UTF-8 text.
            String body = readAll(ex.getRequestBody());
            Map<String, String> f = parseQuery(body);
            String name = f.get("name");
            String email = f.get("email");
            String pass = f.get("password");

            if (name == null || email == null || pass == null) {
                send(ex, 400, "Missing field. Need name, email, password.");
                return;
            }
            if (users.containsKey(email)) {
                send(ex, 409, "User already registered: " + email);
                return;
            }
            users.put(email, name + "|" + pass);
            send(ex, 200,
                "Sign-up SUCCESS\n" +
                "  name : " + name + "\n" +
                "  email: " + email + "\n" +
                "  total registered users: " + users.size());
        }
    }

    // ---- Helpers ----

    // Parse a string like "a=1&b=2" into a Map.
    static Map<String, String> parseQuery(String raw) {
        Map<String, String> m = new HashMap<String, String>();
        if (raw == null || raw.isEmpty()) return m;
        String[] pairs = raw.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            int eq = pair.indexOf('=');
            if (eq < 0) continue;
            try {
                String key = URLDecoder.decode(pair.substring(0, eq), "UTF-8");
                String val = URLDecoder.decode(pair.substring(eq + 1), "UTF-8");
                m.put(key, val);
            } catch (Exception e) {
                // Skip pairs that fail to decode.
            }
        }
        return m;
    }

    // Read all bytes from an input stream into a UTF-8 string.
    static String readAll(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n;
        while ((n = in.read(buf)) != -1) {
            out.write(buf, 0, n);
        }
        return out.toString("UTF-8");
    }

    // Send a response with the given HTTP status code and body.
    static void send(HttpExchange ex, int code, String body) throws IOException {
        byte[] data = body.getBytes("UTF-8");
        ex.sendResponseHeaders(code, data.length);
        OutputStream os = ex.getResponseBody();
        os.write(data);
        os.close();
    }
}

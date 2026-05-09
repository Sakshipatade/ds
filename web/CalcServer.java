import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CalcServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/add",    e -> doMath(e, "add"));
        server.createContext("/sub",    e -> doMath(e, "sub"));
        server.createContext("/mul",    e -> doMath(e, "mul"));
        server.createContext("/div",    e -> doMath(e, "div"));
        server.createContext("/signup", new SignupHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("Web service running on http://localhost:8080");
        System.out.println("Endpoints: /add  /sub  /mul  /div  /signup");
    }

    // ---- Practicals 19..22: arithmetic ----
    static void doMath(HttpExchange ex, String op) throws IOException {
        Map<String, String> q = parseQuery(ex.getRequestURI().getRawQuery());
        String reply;
        try {
            double a = Double.parseDouble(q.getOrDefault("a", "0"));
            double b = Double.parseDouble(q.getOrDefault("b", "0"));
            double r = 0;
            switch (op) {
                case "add": r = a + b; break;
                case "sub": r = a - b; break;
                case "mul": r = a * b; break;
                case "div":
                    if (b == 0) { send(ex, 400, "Error: division by zero"); return; }
                    r = a / b; break;
            }
            reply = op + "(" + a + ", " + b + ") = " + r;
        } catch (NumberFormatException e) {
            send(ex, 400, "Error: a and b must be numbers");
            return;
        }
        send(ex, 200, reply);
    }

    // ---- Practical 23: sign-up ----
    static class SignupHandler implements HttpHandler {
        // in-memory "database"
        static final Map<String, String> users = new HashMap<>();

        @Override
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                send(ex, 405, "Use POST with name=...&email=...&password=...");
                return;
            }
            String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> f = parseQuery(body);
            String name  = f.get("name");
            String email = f.get("email");
            String pass  = f.get("password");

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
                "  name : " + name  + "\n" +
                "  email: " + email + "\n" +
                "  total registered users: " + users.size());
        }
    }

    // ---- helpers ----
    static Map<String, String> parseQuery(String raw) {
        Map<String, String> m = new HashMap<>();
        if (raw == null || raw.isEmpty()) return m;
        for (String pair : raw.split("&")) {
            int eq = pair.indexOf('=');
            if (eq < 0) continue;
            String k = URLDecoder.decode(pair.substring(0, eq), StandardCharsets.UTF_8);
            String v = URLDecoder.decode(pair.substring(eq + 1), StandardCharsets.UTF_8);
            m.put(k, v);
        }
        return m;
    }

    static void send(HttpExchange ex, int code, String body) throws IOException {
        byte[] data = body.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(code, data.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(data); }
    }
}
